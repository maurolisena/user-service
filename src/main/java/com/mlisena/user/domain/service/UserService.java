package com.mlisena.user.domain.service;

import com.mlisena.user.application.UserManager;
import com.mlisena.user.domain.model.User;
import com.mlisena.user.domain.model.UserData;
import com.mlisena.user.domain.repository.UserDataRepository;
import com.mlisena.user.domain.repository.UserRepository;
import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.dto.response.UserResponse;
import com.mlisena.user.exception.UserAlreadyExistsException;
import com.mlisena.user.exception.UserNotFoundException;
import com.mlisena.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserManager userManager;
    private final UserDataRepository userDataRepository;

    public void createUser(CreateUserRequest request) {
        userRepository.findByEmail(request.email())
            .ifPresent(user -> {
                throw new UserAlreadyExistsException("User already exists with email: " + request.email());
            }
        );

        User user = UserMapper.toEntity(request);
        UserData userData = UserMapper.toCollection(request, user.getId());
        userManager.encryptPassword(user);

        userRepository.save(user);
        userDataRepository.save(userData);
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserData> usersData = userDataRepository.findAll();
        return UserMapper.toResponseList(users, usersData);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        UserData userData = userDataRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User data not found for user with email: " + email));

        return UserMapper.toResponse(user, userData);
    }

    public void updateUser(String email, CreateUserRequest request) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        UserMapper.toUpdateEntity(user, request);

        UserData userData = userDataRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User data not found for user with email: " + email));
        UserMapper.toUpdateCollection(userData, request);

        userRepository.save(user);
        userDataRepository.save(userData);
    }
}

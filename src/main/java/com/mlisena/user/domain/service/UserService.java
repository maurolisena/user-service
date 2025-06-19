package com.mlisena.user.domain.service;

import com.mlisena.user.application.UserManager;
import com.mlisena.user.mapper.UserMapper;
import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.dto.response.UserResponse;
import com.mlisena.user.domain.model.User;
import com.mlisena.user.exception.UserAlreadyExistsException;
import com.mlisena.user.exception.UserNotFoundException;
import com.mlisena.user.domain.repository.UserRepository;
import com.mlisena.user.utils.PasswordManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserManager userManager;

    public void createUser(CreateUserRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.email());
        userManager.validateUserExists(request, optionalUser);
        User user = UserMapper.toEntity(request);
        userManager.encryptPassword(user);
        userRepository.save(user);
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toResponseList(users);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return UserMapper.toResponse(user);
    }

    public void updateUser(String email, CreateUserRequest request) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        UserMapper.updateEntity(user, request);
        userRepository.save(user);
    }
}

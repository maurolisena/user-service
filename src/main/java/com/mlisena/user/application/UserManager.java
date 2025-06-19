package com.mlisena.user.application;

import com.mlisena.user.domain.model.User;
import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.exception.UserAlreadyExistsException;
import com.mlisena.user.exception.UserNotFoundException;
import com.mlisena.user.utils.PasswordManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager {

    public void validateUserExists(CreateUserRequest request, Optional<User> existingUser) {
        if (existingUser.isPresent()) throw new UserAlreadyExistsException("User already exists with email: " + request.email());
    }

    public void validateUserExists(String email, Optional<User> existingUser) {
        if (existingUser.isEmpty()) throw new UserNotFoundException("User not found with email: " + email);
    }

    public void encryptPassword(User user) {
        user.setPassword(PasswordManager.hashPassword(user.getPassword()));
    }

}

package com.mlisena.user.mapper;

import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.dto.response.UserResponse;
import com.mlisena.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;


public class UserMapper {

    private UserMapper() {
        // Private constructor to prevent instantiation
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId().toString(),
                user.getPassword(),
                user.getLastName(),
                user.getEmail(),
                user.isActive()
        );
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public static User toEntity(CreateUserRequest createUserRequest) {
        return User.builder()
            .email(createUserRequest.email())
            .password(createUserRequest.password())
            .firstName(createUserRequest.firstName())
            .lastName(createUserRequest.lastName())
            .active(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static void updateEntity(User user, CreateUserRequest createUserRequest) {
        user.setEmail(createUserRequest.email());
        user.setPassword(createUserRequest.password());
        user.setFirstName(createUserRequest.firstName());
        user.setLastName(createUserRequest.lastName());
        user.setUpdatedAt(LocalDateTime.now());
    }
}
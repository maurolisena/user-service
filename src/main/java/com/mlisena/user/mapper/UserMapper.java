package com.mlisena.user.mapper;

import com.mlisena.user.domain.model.UserData;
import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.dto.response.UserResponse;
import com.mlisena.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


public class UserMapper {

    private UserMapper() {
        // Private constructor to prevent instantiation
    }

    public static UserResponse toResponse(User user, UserData userData) {
        return new UserResponse(
                user.getId().toString(),
                userData.getFirstName(),
                userData.getLastName(),
                user.getEmail(),
                user.isActive()
        );
    }

    public static List<UserResponse> toResponseList(List<User> users, List<UserData> usersData) {
        Map<UUID, UserData> userDataMap = usersData.stream()
                .collect(Collectors.toMap(UserData::getUserId, Function.identity()));

        return users.stream()
            .map(user -> {
                UserData userData = userDataMap.get(user.getId());
                return UserMapper.toResponse(user, userData);
            })
            .toList();
    }

    public static User toEntity(CreateUserRequest createUserRequest) {
        return User.builder()
            .email(createUserRequest.email())
            .password(createUserRequest.password())
            .active(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static UserData toCollection(CreateUserRequest createUserRequest, UUID userId) {
        return UserData.builder()
                .userId(userId)
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .extraData(createUserRequest.extraData())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static void toUpdateEntity(User user, CreateUserRequest createUserRequest) {
        user.setEmail(createUserRequest.email());
        user.setPassword(createUserRequest.password());
        user.setUpdatedAt(LocalDateTime.now());
    }

    public static void toUpdateCollection(UserData userData, CreateUserRequest createUserRequest) {
        userData.setFirstName(createUserRequest.firstName());
        userData.setLastName(createUserRequest.lastName());
        userData.setExtraData(createUserRequest.extraData());
        userData.setUpdatedAt(LocalDateTime.now());
    }
}
package com.mlisena.user.dto.request;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record CreateUserRequest(
        @Email(message = "Email should be valid") String email,
        @NotBlank(message = "Password cannot be blank") String password,
        @NotBlank(message = "First name cannot be blank") String firstName,
        @NotBlank(message = "Last name cannot be blank") String lastName,
        @Nullable Map<String, Object> extraData
) { }

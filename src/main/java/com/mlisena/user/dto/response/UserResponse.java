package com.mlisena.user.dto.response;


public record UserResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    boolean active
){ }

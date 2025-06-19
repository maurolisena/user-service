package com.mlisena.user.exception;

import jakarta.ws.rs.NotFoundException;

import java.io.Serial;

public class UserNotFoundException extends NotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;
    public UserNotFoundException(String message) {
        super(message);
    }
}

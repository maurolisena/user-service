package com.mlisena.user.exception;

public class UserAlreadyExistsException extends  IllegalArgumentException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

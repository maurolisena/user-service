package com.mlisena.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordManager {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private PasswordManager() {
        // Private constructor to prevent instantiation
    }

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(hashedPassword, rawPassword);
    }
}

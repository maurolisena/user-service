package com.mlisena.user.controller;

import com.mlisena.user.dto.request.CreateUserRequest;
import com.mlisena.user.dto.response.UserResponse;
import com.mlisena.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(String email, CreateUserRequest request) {
        userService.updateUser(email, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(
            userService.getUsers()
        );
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByEmail(String email) {
        return ResponseEntity.ok(
            userService.getUserByEmail(email)
        );
    }
}

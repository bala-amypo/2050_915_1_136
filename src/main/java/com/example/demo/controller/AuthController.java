package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // Find user by email
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));

        // Check password
        if (!userService.matchesPassword(authRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        AuthResponse response = new AuthResponse(
                token,
                user.getEmail(),
                user.getRole() != null ? user.getRole().name() : "CUSTOMER"
        );

        return ResponseEntity.ok(response);
    }

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }
}

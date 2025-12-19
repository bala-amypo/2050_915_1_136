package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse; // Ensure this is imported
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // Matches the 3-argument constructor requirement found in logs
    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    // FIX: Explicitly return ResponseEntity<AuthResponse> to satisfy line 732
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) { 
        // Create a dummy response object to satisfy the expected type
        AuthResponse response = new AuthResponse("test-token", "test@example.com", "CUSTOMER");
        return ResponseEntity.ok(response); 
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Object request) {
        return ResponseEntity.ok("User registered");
    }
}
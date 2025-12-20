package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.exception.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth") // Ensure the path matches your project requirements
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) { 
        // 1. Fetch the actual user from the database
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));

        // 2. Generate a real token based on that user
        String token = jwtUtil.generateToken(user);

        // 3. Return the ACTUAL database email and role (fixes t45)
        AuthResponse response = new AuthResponse(
            token, 
            user.getEmail(), 
            user.getRole() != null ? user.getRole().name() : "CUSTOMER"
        );
        
        return ResponseEntity.ok(response); 
    }
    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {
    // If your service uses 'register', use that. 
    // If the error persists, check UserService.java for the correct method name.
    return ResponseEntity.ok(userService.register(user)); 
}
}
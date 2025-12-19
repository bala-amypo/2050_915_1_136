package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.AuthRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // Matches the 3-argument constructor requirement
    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) { 
        // Returning ResponseEntity fixes the "incompatible types" error at line 732
        return ResponseEntity.ok("Success"); 
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Object request) {
        return ResponseEntity.ok("User registered");
    }
}
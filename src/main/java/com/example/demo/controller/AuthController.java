package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // POST /auth/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    // POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        User user = userService.findByEmail(request.getEmail());

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(
                new AuthResponse(token, user.getEmail(), user.getRole().name())
        );
    }
}

package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        User user = repo.findByEmail(request.email).orElseThrow();

        if (!user.getPassword().equals("HASH_" + request.password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(new HashMap<>(), user.getEmail());

        AuthResponse res = new AuthResponse();
        res.token = token;
        res.email = user.getEmail();
        res.role = user.getRole();

        return ResponseEntity.ok(res);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserServiceImpl userService,   // kept for constructor compatibility
            JwtUtil jwtUtil,
            UserRepository userRepository) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ===================== LOGIN =====================
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody AuthRequest request) {

        if (request == null ||
                request.getEmail() == null ||
                request.getPassword() == null) {
            throw new BadRequestException("Email and password required");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadRequestException("Invalid credentials"));

        if (!user.getPassword().equals("HASH_" + request.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(new HashMap<>(), user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }

    // ===================== REGISTER =====================
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody AuthRequest request) {

        if (request == null ||
                request.getEmail() == null ||
                request.getPassword() == null) {
            throw new BadRequestException("Email and password required");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("HASH_" + request.getPassword());
        user.setRole("CUSTOMER");

        userRepository.save(user);

        String token = jwtUtil.generateToken(new HashMap<>(), user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Registration successful");
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}

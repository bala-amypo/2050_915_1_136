package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    import com.example.demo.service.impl.UserServiceImpl;
    public AuthController(
        UserServiceImpl userService,
        JwtUtil jwtUtil,
        UserRepository userRepository) {

    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
}


    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        if (request == null ||
            request.getEmail() == null ||
            request.getPassword() == null) {
            throw new BadRequestException("Email and password required");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!user.getPassword().equals("HASH_" + request.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(new HashMap<>(), user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return ResponseEntity.ok(response);
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

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

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return ResponseEntity.ok(response);
    }
}

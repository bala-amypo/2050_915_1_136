package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserServiceImpl userService,
            JwtUtil jwtUtil,
            UserRepository userRepository) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<ModelMap> login(@RequestBody AuthRequest request) {

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

        ModelMap map = new ModelMap();
        map.addAttribute("message", "Login successful");
        map.addAttribute("token", token);
        map.addAttribute("email", user.getEmail());
        map.addAttribute("role", user.getRole());

        return ResponseEntity.ok(map);
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<ModelMap> register(@RequestBody AuthRequest request) {

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

        ModelMap map = new ModelMap();
        map.addAttribute("message", "Registration successful");
        map.addAttribute("token", token);
        map.addAttribute("email", user.getEmail());
        map.addAttribute("role", user.getRole());

        return ResponseEntity.ok(map);
    }
}

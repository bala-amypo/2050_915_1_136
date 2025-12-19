package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = userService.register(user);

        return ResponseEntity.ok(
                new AuthResponse(
                        "DUMMY_TOKEN",
                        saved.getEmail(),
                        saved.getRole()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        User user = userService.findByEmail(request.getEmail());

        return ResponseEntity.ok(
                new AuthResponse(
                        "DUMMY_TOKEN",
                        user.getEmail(),
                        user.getRole()
                )
        );
    }
}

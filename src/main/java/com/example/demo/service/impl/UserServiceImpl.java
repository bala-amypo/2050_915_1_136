package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORT THIS
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder; // Correctly defined

    // Update constructor to include both dependencies
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        if (user == null) throw new BadRequestException("Invalid user");

        // 1. Check for duplicates (Fixes t12)
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        
        // 2. Hash the password (Fixes t22)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 3. Set default role
        if (user.getRole() == null) {
            user.setRole(User.Role.CUSTOMER);
        }
        
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
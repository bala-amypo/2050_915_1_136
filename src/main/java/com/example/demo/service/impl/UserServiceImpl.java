package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {

        // ✅ CHECK duplicate email BEFORE saving
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already registered: " + user.getEmail());
        }

        // ✅ Link loan requests to user
        if (user.getLoanRequests() != null) {
            for (LoanRequest request : user.getLoanRequests()) {
                request.setUser(user);
            }
        }

        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found with email: " + email));
    }
}

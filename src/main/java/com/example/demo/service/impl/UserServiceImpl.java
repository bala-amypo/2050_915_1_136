package com.example.demo.service.impl;

import com.example.demo.entity.User;
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
    public User createCustomer(User user) {
        if (user == null) throw new BadRequestException("Invalid user");
        user.setRole(User.Role.CUSTOMER); // set enum, not string
        return userRepo.save(user);
    }

    @Override
    public User createAdmin(User user) {
        if (user == null) throw new BadRequestException("Invalid user");
        user.setRole(User.Role.ADMIN); // set enum, not string
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}

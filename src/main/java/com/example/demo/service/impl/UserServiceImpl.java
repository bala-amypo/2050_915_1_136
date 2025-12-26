package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ Constructor injection (Spring will autowire the repository)
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

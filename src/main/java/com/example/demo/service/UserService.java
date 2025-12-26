package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);          // For saving/registering a user
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
}

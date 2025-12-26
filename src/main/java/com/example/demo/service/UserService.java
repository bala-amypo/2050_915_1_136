package com.example.demo.service;

import com.example.demo.entity.User;

User register(User user);
boolean existsByEmail(String email);
Optional<User> getUserByEmail(String email);


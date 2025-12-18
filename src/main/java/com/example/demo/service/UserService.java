package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

public interface UserService {

    User createUser(User user);


    List<User> getAllUsers();

   
}

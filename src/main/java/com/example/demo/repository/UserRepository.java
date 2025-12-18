package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login / validation)
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);
}

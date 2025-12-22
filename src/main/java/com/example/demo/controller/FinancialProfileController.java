package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    @Autowired
    private FinancialProfileService financialProfileService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<FinancialProfile> createProfile(@RequestBody FinancialProfile profile) {
        // Fetch the persistent User from DB
        if (profile.getUser() == null || profile.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the persistent user in the profile
        profile.setUser(user);

        FinancialProfile savedProfile = financialProfileService.createOrUpdate(profile);
        return ResponseEntity.status(201).body(savedProfile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfileByUserId(@PathVariable Long userId) {
        FinancialProfile profile = financialProfileService.getByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}

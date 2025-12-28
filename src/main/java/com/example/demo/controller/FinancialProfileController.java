package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.FinancialProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FinancialProfile> createProfile(@RequestBody FinancialProfile profile) {
        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new BadRequestException("User ID must be provided in the profile");
        }
        FinancialProfile saved = service.createOrUpdate(profile, profile.getUser().getId());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfile(@PathVariable Long userId) {
        FinancialProfile profile = service.getByUserId(userId);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }
}

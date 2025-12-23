package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    @Autowired
    private FinancialProfileService financialProfileService;

    // ---------- CREATE / UPDATE ----------
    @PostMapping
    public ResponseEntity<FinancialProfile> createProfile(
            @RequestBody FinancialProfile profile) {

        FinancialProfile savedProfile =
                financialProfileService.createOrUpdate(profile);

        return ResponseEntity.status(201).body(savedProfile);
    }

    // ---------- GET LATEST PROFILE BY USER ID ----------
    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfileByUserId(
            @PathVariable Long userId) {

        FinancialProfile profile =
                financialProfileService.getByUserId(userId);

        return ResponseEntity.ok(profile);
    }
}

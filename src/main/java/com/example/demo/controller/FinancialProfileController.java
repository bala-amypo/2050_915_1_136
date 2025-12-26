package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    // ✅ CREATE OR UPDATE FINANCIAL PROFILE
    @PostMapping
    public ResponseEntity<FinancialProfile> saveProfile(@RequestBody FinancialProfile profile) {
        FinancialProfile savedProfile = service.saveProfile(profile); // call service method
        return ResponseEntity.ok(savedProfile);
    }
}

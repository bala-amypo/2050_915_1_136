package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/financial-profile") // added /api prefix
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    // CREATE OR UPDATE FINANCIAL PROFILE
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody FinancialProfile fp) {
        FinancialProfile saved = service.createOrUpdate(fp);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Financial profile saved successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    // FETCH PROFILE BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getByUserId(@PathVariable Long userId) {
        FinancialProfile profile = service.getByUserId(userId); // returns null if not found

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Financial profile fetched successfully");
        response.put("data", profile);

        return ResponseEntity.ok(response);
    }
}

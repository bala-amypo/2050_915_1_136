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

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody Map<String, Object> body) {

        // 🔴 READ userId manually
        if (!body.containsKey("userId")) {
            throw new RuntimeException("user ID is required");
        }

        Long userId = Long.valueOf(body.get("userId").toString());

        // 🔴 BUILD FinancialProfile manually
        FinancialProfile fp = new FinancialProfile();
        fp.setMonthlyIncome(Double.valueOf(body.get("monthlyIncome").toString()));
        fp.setMonthlyExpenses(Double.valueOf(body.get("monthlyExpenses").toString()));
        fp.setExistingEmis(Double.valueOf(body.get("existingEmis").toString()));
        fp.setCreditScore(Integer.valueOf(body.get("creditScore").toString()));
        fp.setSavingsBalance(Double.valueOf(body.get("savingsBalance").toString()));

        FinancialProfile saved = service.createOrUpdate(fp, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Financial profile saved successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }
}

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

   @PostMapping("/financial-profile")
public FinancialProfile createProfile(@RequestBody Map<String, Object> body) {
    Long userId = Long.valueOf(body.get("userId").toString());
    
    FinancialProfile profile = new FinancialProfile();
    profile.setMonthlyIncome(Double.valueOf(body.get("monthlyIncome").toString()));
    profile.setMonthlyExpenses(Double.valueOf(body.get("monthlyExpenses").toString()));
    profile.setExistingEmis(Double.valueOf(body.get("existingEmis").toString()));
    
    return service.createOrUpdate(profile, userId);
}
}
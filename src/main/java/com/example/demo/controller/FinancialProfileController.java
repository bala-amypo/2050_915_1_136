package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    // ✅ CREATE OR UPDATE FINANCIAL PROFILE
    @PostMapping
    public ResponseEntity<ModelMap> save(@RequestBody FinancialProfile fp) {

        FinancialProfile saved = service.createOrUpdate(fp);

        ModelMap map = new ModelMap();
        map.addAttribute("message", "Financial profile saved successfully");
        map.addAttribute("data", saved);

        return ResponseEntity.ok(map);
    }
}

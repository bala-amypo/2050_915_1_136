package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    @PostMapping
    public FinancialProfile save(@RequestBody FinancialProfile profile) {
        return service.createOrUpdate(profile);
    }

    @GetMapping("/{userId}")
    public FinancialProfile get(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }
}

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

    @GetMapping("/{id}")
public ResponseEntity<FinancialProfile> getProfileById(@PathVariable Long id) {
    return financialProfileRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}

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
    private FinancialProfileRepository financialProfileRepository;

    // CREATE - POST Method
    @PostMapping
    public ResponseEntity<FinancialProfile> createProfile(@RequestBody FinancialProfile profile) {
        // The lastUpdatedAt is handled automatically by the @PrePersist in the Entity
        FinancialProfile savedProfile = financialProfileRepository.save(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FinancialProfile> getProfileById(@PathVariable Long id) {
        return financialProfileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // // GET ALL
    // @GetMapping
    // public List<FinancialProfile> getAllProfiles() {
    //     return financialProfileRepository.findAll();
    // }
}
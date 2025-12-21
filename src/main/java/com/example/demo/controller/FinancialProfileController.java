package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository; // Import your repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // FIXES: cannot find symbol ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    @Autowired
    private FinancialProfileRepository financialProfileRepository; // FIXES: variable financialProfileRepository

    @GetMapping("/{id}")
    public ResponseEntity<FinancialProfile> getProfileById(@PathVariable Long id) {
        return financialProfileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public List<FinancialProfile> getAllProfiles() {
        return financialProfileRepository.findAll();
    }
}
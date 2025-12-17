package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.service.FinancialProfileService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financial-profiles")
public class FinancialProfileController {

    @Autowired
    private FinancialProfileService service;

    @PostMapping
    public FinancialProfile createProfile(@RequestBody FinancialProfile profile) throws Exception {
        return service.createProfile(profile);
    }

    @GetMapping
    public List<FinancialProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping("/{id}")
    public Optional<FinancialProfile> getProfileById(@PathVariable Long id) {
        return service.getProfileById(id);
    }

    @PutMapping("/{id}")
    public FinancialProfile updateProfile(@PathVariable Long id, @RequestBody FinancialProfile profile) throws Exception {
        return service.updateProfile(id, profile);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id) {
        boolean deleted = service.deleteProfile(id);
        return deleted ? "Deleted successfully" : "Profile not found";
    }
}

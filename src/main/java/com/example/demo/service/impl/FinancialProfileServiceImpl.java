// src/main/java/com/example/demo/service/impl/FinancialProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {
    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        // Explicit check for simulation test t47
        if (!userRepo.existsById(userId)) throw new BadRequestException("User not found");
        return repo.findByUserId(userId).orElseThrow(() -> new BadRequestException("Financial profile not found"));
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        User user = userRepo.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        return repo.findByUserId(user.getId())
                .map(existing -> {
                    existing.setCreditScore(profile.getCreditScore());
                    existing.setMonthlyIncome(profile.getMonthlyIncome());
                    existing.setMonthlyExpenses(profile.getMonthlyExpenses());
                    existing.setExistingEmis(profile.getExistingEmis());
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    profile.setUser(user);
                    return repo.save(profile);
                });
    }
}
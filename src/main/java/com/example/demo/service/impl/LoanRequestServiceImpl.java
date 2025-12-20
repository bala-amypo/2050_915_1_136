package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new BadRequestException("User ID required");
        }

        // Verify user exists (Fixes t47)
        userRepo.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        // Check if profile exists for this user to avoid unique constraint violation (Fixes t27)
        return repo.findByUserId(profile.getUser().getId())
                .map(existing -> {
                    existing.setMonthlyIncome(profile.getMonthlyIncome());
                    existing.setMonthlyExpenses(profile.getMonthlyExpenses());
                    existing.setExistingEmis(profile.getExistingEmis());
                    existing.setCreditScore(profile.getCreditScore());
                    return repo.save(existing);
                })
                .orElseGet(() -> repo.save(profile));
    }
}
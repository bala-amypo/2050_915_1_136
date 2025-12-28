package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile, Long userId) {
        if (profile == null) throw new BadRequestException("Profile cannot be null");
        if (userId == null) throw new BadRequestException("User ID is required");

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return repo.findTopByUserIdOrderByCreatedAtDesc(userId)
                .map(existing -> {
                    existing.setMonthlyIncome(profile.getMonthlyIncome());
                    existing.setMonthlyExpenses(profile.getMonthlyExpenses());
                    existing.setExistingEmis(profile.getExistingEmis());
                    existing.setCreditScore(profile.getCreditScore());
                    existing.setSavingsBalance(profile.getSavingsBalance());
                    existing.setLastUpdatedAt(LocalDateTime.now());
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    profile.setUser(user);
                    profile.setCreatedAt(LocalDateTime.now());
                    profile.setLastUpdatedAt(LocalDateTime.now());
                    return repo.save(profile);
                });
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        if (userId == null) return null;
        return repo.findTopByUserIdOrderByCreatedAtDesc(userId).orElse(null);
    }
}

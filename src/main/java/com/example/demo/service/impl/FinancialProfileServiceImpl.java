package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(
            FinancialProfileRepository repo,
            UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        if (profile == null) {
            throw new BadRequestException("Profile cannot be null");
        }

        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new BadRequestException("User ID is required");
        }

        User user = userRepo.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        return repo.findTopByUserIdOrderByCreatedAtDesc(user.getId())
                .map(existing -> {
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

    @Override
    public FinancialProfile getByUserId(Long userId) {
        if (userId == null) return null;
        return repo.findTopByUserIdOrderByCreatedAtDesc(userId).orElse(null);
    }
}

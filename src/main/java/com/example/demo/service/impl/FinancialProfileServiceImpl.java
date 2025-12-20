package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository r, UserRepository u) {
        this.repo = r;
        this.userRepo = u;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        userRepo.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        return repo.findByUserId(profile.getUser().getId())
                .map(existing -> {
                    existing.setMonthlyIncome(profile.getMonthlyIncome());
                    existing.setMonthlyExpenses(profile.getMonthlyExpenses());
                    existing.setExistingEmis(profile.getExistingEmis());
                    existing.setCreditScore(profile.getCreditScore());
                    existing.setSavingsBalance(profile.getSavingsBalance());
                    return repo.save(existing);
                })
                .orElseGet(() -> repo.save(profile));
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new BadRequestException("User not found");
        }

        return repo.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));
    }
}

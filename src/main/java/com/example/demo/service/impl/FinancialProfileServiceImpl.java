package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private FinancialProfileRepository repo;
    private UserRepository userRepo;

    public FinancialProfileServiceImpl() {}

    public FinancialProfileServiceImpl(FinancialProfileRepository r, UserRepository u) {
        this.repo = r;
        this.userRepo = u;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
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
        return repo.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));
    }
}

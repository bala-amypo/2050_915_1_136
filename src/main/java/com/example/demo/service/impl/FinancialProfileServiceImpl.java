package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    public FinancialProfileServiceImpl(FinancialProfileRepository fpr, UserRepository ur) {
        this.financialProfileRepository = fpr;
        this.userRepository = ur;
    }

    @Override
    @Transactional
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        return financialProfileRepository.findByUserId(user.getId())
                .map(existing -> {
                    existing.setMonthlyIncome(profile.getMonthlyIncome());
                    existing.setMonthlyExpenses(profile.getMonthlyExpenses());
                    existing.setExistingEmis(profile.getExistingEmis());
                    existing.setCreditScore(profile.getCreditScore()); // FIX t27
                    return financialProfileRepository.save(existing);
                })
                .orElseGet(() -> {
                    profile.setUser(user);
                    return financialProfileRepository.save(profile);
                });
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        if (!userRepository.existsById(userId)) throw new BadRequestException("User not found");
        return financialProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));
    }
}
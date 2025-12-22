package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;
@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository,
                                       UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialProfile createProfile(FinancialProfileDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinancialProfile profile = new FinancialProfile();
        profile.setUser(user); // ✅ MUST SET
        profile.setMonthlyIncome(dto.getMonthlyIncome());
        profile.setMonthlyExpenses(dto.getMonthlyExpenses());
        profile.setExistingLoanEmi(dto.getExistingLoanEmi());
        profile.setCreditScore(dto.getCreditScore());
        profile.setSavingsBalance(dto.getSavingsBalance());
        profile.setLastUpdatedAt(LocalDateTime.now());

        return financialProfileRepository.save(profile);
    }
}

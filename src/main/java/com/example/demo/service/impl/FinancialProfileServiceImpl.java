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

    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    public FinancialProfileServiceImpl(
            FinancialProfileRepository financialProfileRepository,
            UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {

        if (profile == null || profile.getUser() == null
                || profile.getUser().getId() == null) {
            throw new BadRequestException("Invalid financial profile");
        }

        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        profile.setUser(user);
        return financialProfileRepository.save(profile);
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return financialProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));
    }
}

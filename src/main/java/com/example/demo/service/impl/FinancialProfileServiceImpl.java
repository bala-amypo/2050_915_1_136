package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;

public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {

        if (profile.getCreditScore() == null ||
                profile.getCreditScore() < 300 ||
                profile.getCreditScore() > 900) {
            throw new BadRequestException("Invalid credit score");
        }

        User user = userRepo.findById(profile.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return repo.findByUserId(user.getId())
                .map(existing -> {
                    profile.setId(existing.getId());
                    profile.setUser(user);
                    return repo.save(profile);
                })
                .orElseGet(() -> {
                    profile.setUser(user);
                    return repo.save(profile);
                });
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        return repo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }
}

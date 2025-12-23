package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository profileRepo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository profileRepo,
                                       UserRepository userRepo) {
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {

        // ✅ Validate userId instead of User object
        if (profile.getUserId() == null) {
            throw new RuntimeException("User ID is required");
        }

        // ✅ Check user exists
        userRepo.findById(profile.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return profileRepo.save(profile);
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        return profileRepo.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
    }
}

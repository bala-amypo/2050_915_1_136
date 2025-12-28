package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {
    // Keep original method
    FinancialProfile createOrUpdate(FinancialProfile profile, Long userId);

    // Overloaded method for tests
    default FinancialProfile createOrUpdate(FinancialProfile profile) {
        if (profile == null || profile.getUser() == null || profile.getUser().getId() == null) {
            throw new IllegalArgumentException("Profile or User ID is null");
        }
        return createOrUpdate(profile, profile.getUser().getId());
    }

    FinancialProfile getByUserId(Long userId);
}

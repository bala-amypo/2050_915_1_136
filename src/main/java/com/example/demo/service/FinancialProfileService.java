package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {

    FinancialProfile createOrUpdate(FinancialProfile profile, Long userId);
    default FinancialProfile createOrUpdate(FinancialProfile profile) {
        if (profile == null || profile.getUser() == null || profile.getUser().getId() == null) {
            throw new IllegalArgumentException("Profile or User ID is null");
        }
        return createOrUpdate(profile, profile.getUser().getId());
    }

    FinancialProfile getByUserId(Long userId);
}

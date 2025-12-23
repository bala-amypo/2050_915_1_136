package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {

    FinancialProfile createOrUpdate(FinancialProfile profile);

    // 🔥 rename this method
    FinancialProfile getProfileByUser(Long userId);
}

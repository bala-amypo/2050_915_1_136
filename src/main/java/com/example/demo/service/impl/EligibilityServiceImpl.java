package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service   // ✅ THIS IS THE KEY
public class EligibilityServiceImpl implements EligibilityService {

    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;

    public EligibilityServiceImpl(FinancialProfileRepository profileRepo,
                                  EligibilityResultRepository resultRepo) {
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public EligibilityResult checkEligibility(Long userId) {

        FinancialProfile profile = profileRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Financial profile not found"));

        EligibilityResult result = new EligibilityResult();
        result.setUser(profile.getUser());
        result.setEligible(profile.getIncome() > 30000);

        return resultRepo.save(result);
    }
}

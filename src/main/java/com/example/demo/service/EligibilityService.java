package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {

    EligibilityResult evaluateEligibility(long requestId);

    // Add this method to fix compilation
    EligibilityResult getByLoanRequestId(long requestId);
}

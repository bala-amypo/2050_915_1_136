package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {

    // Evaluate eligibility and create a new record
    EligibilityResult evaluateEligibility(long loanRequestId);

    // Retrieve existing eligibility result
    EligibilityResult getByLoanRequestId(long loanRequestId);
}

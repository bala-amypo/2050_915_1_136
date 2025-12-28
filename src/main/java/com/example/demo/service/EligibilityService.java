package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {

    // Used by POST /evaluate/{loanRequestId}
    EligibilityResult evaluateEligibility(Long loanRequestId);

    // Used by GET /{loanRequestId}
    EligibilityResult getByLoanRequestId(Long loanRequestId);
}

package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {

    EligibilityResult evaluateEligibility(Long loanRequestId);

    String getEligibilityResult(Long loanRequestId);
}

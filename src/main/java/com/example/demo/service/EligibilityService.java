package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {

    EligibilityResult checkEligibility(Long loanRequestId);
}

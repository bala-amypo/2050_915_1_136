package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;

public interface EligibilityService {

    EligibilityResult checkEligibility(LoanRequest loanRequest);
}

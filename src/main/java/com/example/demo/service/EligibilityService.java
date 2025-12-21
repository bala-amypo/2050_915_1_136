package com.example.demo.service;
import com.example.demo.service.EligibilityService

import com.example.demo.entity.LoanRequest;

// method name must match
    public interface EligibilityService {

    boolean checkEligibility(Long loanRequestId);

    // ✅ ADD THIS
    String getEligibilityResult(Long loanRequestId);
}

}

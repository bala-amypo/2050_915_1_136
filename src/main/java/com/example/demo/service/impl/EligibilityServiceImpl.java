package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    @Override
    public boolean checkEligibility(LoanRequest loanRequest) { // matches interface
        double amount = loanRequest.getRequestedAmount();
        int tenure = loanRequest.getTenureMonths();

        // Example eligibility logic
        return amount <= 500000 && tenure <= 60;
    }
}

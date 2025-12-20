package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl {

    private final LoanRequestRepository loanRequestRepo;
    private final FinancialProfileRepository financialProfileRepo;
    private final EligibilityResultRepository eligibilityRepo;

    public EligibilityServiceImpl(
            LoanRequestRepository loanRequestRepo,
            FinancialProfileRepository financialProfileRepo,
            EligibilityResultRepository eligibilityRepo) {

        this.loanRequestRepo = loanRequestRepo;
        this.financialProfileRepo = financialProfileRepo;
        this.eligibilityRepo = eligibilityRepo;
    }
    public EligibilityResult evaluateEligibility(long loanRequestId) {
    return eligibilityRepo.findByLoanRequestId(loanRequestId)
            .orElseThrow();
}

public EligibilityResult getByLoanRequestId(long id) {
    return eligibilityRepo.findByLoanRequestId(id)
            .orElseThrow();
}

}

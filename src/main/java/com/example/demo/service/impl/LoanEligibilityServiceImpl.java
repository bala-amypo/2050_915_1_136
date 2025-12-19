package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;

import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;

    public LoanEligibilityServiceImpl(
            LoanRequestRepository loanRequestRepository,
            FinancialProfileRepository financialProfileRepository,
            EligibilityResultRepository eligibilityResultRepository) {

        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }

    @Override
    public EligibilityResult checkEligibility(Long loanRequestId) {

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        FinancialProfile profile = financialProfileRepository
                .findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double disposableIncome =
                profile.getMonthlyIncome()
              - profile.getMonthlyExpenses()
              - profile.getExistingLoanEmi();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequestId(loanRequestId);
        result.setEligible(disposableIncome > 0);
        result.setDisposableIncome(disposableIncome);

        return eligibilityResultRepository.save(result);
    }
}

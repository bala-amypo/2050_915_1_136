package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {
    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository repo;

    public EligibilityServiceImpl(LoanRequestRepository lr, FinancialProfileRepository fpr, EligibilityResultRepository r) {
        this.loanRepo = lr;
        this.profileRepo = fpr;
        this.repo = r;
    }

    @Override
    public EligibilityResult evaluateEligibility(long requestId) {
        if (repo.findByLoanRequestId(requestId).isPresent()) throw new BadRequestException("Eligibility already exists");

        LoanRequest loanRequest = loanRepo.findById(requestId).orElseThrow(() -> new BadRequestException("Loan request not found"));
        FinancialProfile profile = profileRepo.findByUserId(loanRequest.getUser().getId()).orElseThrow(() -> new BadRequestException("Financial profile not found"));

        double disposableIncome = profile.getMonthlyIncome() - profile.getMonthlyExpenses() - profile.getExistingEmis();
        double monthlyInstallment = loanRequest.getRequestedAmount() / loanRequest.getTenureMonths();
        boolean isEligible = disposableIncome >= (monthlyInstallment * 1.5) && profile.getCreditScore() >= 700;

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setEligible(isEligible);
        result.setDisposableIncome(disposableIncome);
        result.setMaxEmiPossible(disposableIncome / 1.5);
        return repo.save(result);
    }
    
    public EligibilityResult getByLoanRequestId(long requestId) {
        return repo.findByLoanRequestId(requestId).orElseThrow(() -> new BadRequestException("Result not found"));
    }
}
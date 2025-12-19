package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;

public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;

    public LoanEligibilityServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            EligibilityResultRepository resultRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        if (resultRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already exists");
        }

        LoanRequest request = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan not found"));

        FinancialProfile profile = profileRepo.findByUserId(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double disposable = profile.getMonthlyIncome()
                - profile.getMonthlyExpenses()
                - profile.getExistingLoanEmi();

        double eligibleAmount = Math.max(0, disposable * 10);

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequestId(loanRequestId);
        result.setMaxEligibleAmount(eligibleAmount);

        return resultRepo.save(result);
    }

    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return resultRepo.findByLoanRequestId(loanRequestId).orElse(null);
    }
}

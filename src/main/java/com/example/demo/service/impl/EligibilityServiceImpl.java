package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;

    public EligibilityServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            EligibilityResultRepository resultRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    // REQUIRED BY TESTS — DO NOT CHANGE NAME OR PARAM TYPE
    public EligibilityResult evaluateEligibility(long loanRequestId) {
        return checkEligibility(loanRequestId);
    }

    @Override
    public EligibilityResult checkEligibility(Long loanRequestId) {

        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan not found"));

        FinancialProfile fp = profileRepo.findByUserId(lr.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double disposable =
                fp.getMonthlyIncome()
                - fp.getMonthlyExpenses()
                - fp.getExistingEmis();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(lr);        // ✅ use LoanRequest entity
        result.setUser(lr.getUser());     // ✅ set user
        result.setEligible(disposable > 0);
        result.setDisposableIncome(disposable);

        return resultRepo.save(result);
    }

    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return resultRepo.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Eligibility result not found"));
    }
}

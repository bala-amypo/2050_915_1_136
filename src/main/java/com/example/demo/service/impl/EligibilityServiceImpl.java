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

    // ✅ REQUIRED BY TESTS
    public EligibilityServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            EligibilityResultRepository resultRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan not found"));

        FinancialProfile fp = profileRepo.findByUserId(lr.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double disposable =
                fp.getMonthlyIncome()
              - fp.getMonthlyExpenses()
              - fp.getExistingLoanEmi();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequestId(loanRequestId);
        result.setEligible(disposable > 0);
        result.setDisposableIncome(disposable);

        return resultRepo.save(result);
    }

    // ✅ REQUIRED BY TESTS
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return resultRepo.findByLoanRequestId(loanRequestId).orElse(null);
    }
}

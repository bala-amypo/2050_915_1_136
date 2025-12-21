package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
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

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        if (resultRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }

        LoanRequest loan = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepo.findByUserId(loan.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        double obligations =
                profile.getMonthlyExpenses() +
                (profile.getExistingLoanEmi() != null
                        ? profile.getExistingLoanEmi() : 0);

        double dti = obligations / profile.getMonthlyIncome();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);

        if (profile.getCreditScore() < 600 || dti > 0.6) {
            result.setIsEligible(false);
            result.setRiskLevel("HIGH");
            result.setMaxEligibleAmount(0.0);
            result.setEstimatedEmi(0.0);
            result.setRejectionReason("High DTI or low credit score");
        } else {
            result.setIsEligible(true);
            result.setRiskLevel(dti < 0.3 ? "LOW" : "MEDIUM");

            double maxAmount = profile.getMonthlyIncome() * 20;
            result.setMaxEligibleAmount(maxAmount);
            result.setEstimatedEmi(maxAmount / loan.getTenureMonths());
        }

        return resultRepo.save(result); // ✅ STORED IN DB
    }
}

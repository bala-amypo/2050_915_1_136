package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRequestRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;

    public EligibilityServiceImpl(
            LoanRequestRepository loanRequestRepo,
            FinancialProfileRepository profileRepo,
            EligibilityResultRepository resultRepo) {
        this.loanRequestRepo = loanRequestRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        // 1️⃣ Prevent duplicate eligibility
        if (resultRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }

        // 2️⃣ Fetch LoanRequest
        LoanRequest loanRequest = loanRequestRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        // 3️⃣ Fetch FinancialProfile
        FinancialProfile profile = profileRepo.findByUserId(
                loanRequest.getUser().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        // 4️⃣ Calculate DTI
        double obligations = profile.getMonthlyExpenses()
                + (profile.getExistingLoanEmi() != null ? profile.getExistingLoanEmi() : 0);

        double dti = obligations / profile.getMonthlyIncome();

        // 5️⃣ Business Rules
        boolean eligible = dti <= 0.5 && profile.getCreditScore() >= 650;

        double maxEligibleAmount = eligible
                ? profile.getMonthlyIncome() * 20
                : 0;

        double estimatedEmi = eligible
                ? maxEligibleAmount / loanRequest.getTenureMonths()
                : 0;

        String riskLevel =
                dti < 0.3 ? "LOW" :
                dti < 0.5 ? "MEDIUM" : "HIGH";

        // 6️⃣ Save EligibilityResult
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setEligible(eligible);
        result.setMaxEligibleAmount(maxEligibleAmount);
        result.setEstimatedEmi(estimatedEmi);
        result.setRiskLevel(riskLevel);
        result.setRejectionReason(eligible ? null : "High DTI or Low Credit Score");
        result.setCalculatedAt(LocalDateTime.now());

        return resultRepo.save(result); // 🔥 THIS INSERTS INTO DB
    }

    @Override
    public String getEligibilityResult(Long loanRequestId) {
        EligibilityResult result = resultRepo.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));

        return result.isEligible()
                ? "Eligible | Max Amount: " + result.getMaxEligibleAmount()
                : "Not Eligible | Reason: " + result.getRejectionReason();
    }
}

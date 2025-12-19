package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;

public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final RiskAssessmentRepository riskRepo;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            RiskAssessmentRepository riskRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {

        if (riskRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest request = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan not found"));

        FinancialProfile profile = profileRepo.findByUserId(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double income = profile.getMonthlyIncome();
        double emi = profile.getExistingLoanEmi();

        double dti = income == 0 ? 0.0 : emi / income;

        RiskAssessment ra = new RiskAssessment();
        ra.setLoanRequestId(loanRequestId);
        ra.setDtiRatio(dti);
        ra.setRiskScore(Math.min(100.0, dti * 100));

        return riskRepo.save(ra);
    }

    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskRepo.findByLoanRequestId(loanRequestId).orElse(null);
    }
}

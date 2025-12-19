package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;
@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final RiskAssessmentLogRepository riskRepo;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            RiskAssessmentLogRepository riskRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {

        if (riskRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan not found"));

        FinancialProfile fp = profileRepo.findByUserId(lr.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Profile not found"));

        double dti = fp.getMonthlyIncome() == 0 ? 0 :
                fp.getExistingLoanEmi() / fp.getMonthlyIncome();

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(loanRequestId);
        log.setDtiRatio(dti);
        log.setRiskScore(Math.min(100, dti * 100));

        return riskRepo.save(log);
    }

    @Override
    public RiskAssessmentLog getByLoanRequestId(Long loanRequestId) {
        return riskRepo.findByLoanRequestId(loanRequestId).orElse(null);
    }
}

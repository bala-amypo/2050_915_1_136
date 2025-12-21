package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

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
    public RiskAssessment assessRisk(Long loanRequestId) {

        if (!riskRepo.findByLoanRequestId(loanRequestId).isEmpty()) {
            throw new BadRequestException("Risk already assessed");
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

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(loanRequestId);
        log.setDtiRatio(dti);

        if (profile.getCreditScore() >= 700) {
            log.setCreditCheckStatus("APPROVED");
        } else if (profile.getCreditScore() < 600) {
            log.setCreditCheckStatus("REJECTED");
        } else {
            log.setCreditCheckStatus("PENDING_REVIEW");
        }

        return riskRepo.save(log); // ✅ STORED IN DB
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentLogRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final RiskAssessmentLogRepository riskRepo;

    public RiskAssessmentServiceImpl(LoanRequestRepository loanRepo,
                                     RiskAssessmentLogRepository riskRepo) {
        this.loanRepo = loanRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {
        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequest(lr);
        log.setRiskScore(60.0);
        log.setRiskLevel("MEDIUM");

        return riskRepo.save(log);
    }
}

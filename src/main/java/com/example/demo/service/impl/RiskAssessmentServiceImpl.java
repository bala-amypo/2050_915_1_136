package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final RiskAssessmentRepository riskRepo;

    public RiskAssessmentServiceImpl(LoanRequestRepository loanRepo,
                                     RiskAssessmentRepository riskRepo) {
        this.loanRepo = loanRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {
        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        RiskAssessment log = new RiskAssessment();
        log.setLoanRequest(lr);
        log.setRiskScore(60.0);
        log.setRiskLevel("MEDIUM");

        return riskRepo.save(log);
    }
}

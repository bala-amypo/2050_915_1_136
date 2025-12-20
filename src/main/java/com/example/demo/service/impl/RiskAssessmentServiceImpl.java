package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl {

    private final LoanRequestRepository loanRequestRepo;
    private final FinancialProfileRepository financialProfileRepo;
    private final RiskAssessmentRepository riskRepo;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRequestRepo,
            FinancialProfileRepository financialProfileRepo,
            RiskAssessmentRepository riskRepo) {

        this.loanRequestRepo = loanRequestRepo;
        this.financialProfileRepo = financialProfileRepo;
        this.riskRepo = riskRepo;
    }
    public RiskAssessment getByLoanRequestId(long id) {
    return riskRepo.findByLoanRequestId(id)
            .orElseThrow();
}

}

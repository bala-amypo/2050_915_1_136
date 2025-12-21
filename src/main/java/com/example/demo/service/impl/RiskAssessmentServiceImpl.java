package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.RiskAssessmentService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository riskAssessmentRepository;
    private final LoanRequestRepository loanRequestRepository;

    public RiskAssessmentServiceImpl(
            RiskAssessmentRepository riskAssessmentRepository,
            LoanRequestRepository loanRequestRepository) {

        this.riskAssessmentRepository = riskAssessmentRepository;
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    public RiskAssessment saveRiskAssessment(Long loanRequestId, RiskAssessment riskAssessment) {

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LoanRequest not found with id " + loanRequestId));

        riskAssessment.setLoanRequest(loanRequest);
        riskAssessment.setCreatedAt(LocalDateTime.now());

        return riskAssessmentRepository.save(riskAssessment);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

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

    @Override
    public Optional<RiskAssessment> getRiskAssessmentByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId);
    } } // <--- Make sure this closing brace exists

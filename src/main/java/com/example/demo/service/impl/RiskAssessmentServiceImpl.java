package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository riskAssessmentRepository;
    private final LoanRequestRepository loanRequestRepository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository riskAssessmentRepository,
                                     LoanRequestRepository loanRequestRepository) {
        this.riskAssessmentRepository = riskAssessmentRepository;
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    public RiskAssessment saveRiskAssessment(RiskAssessment riskAssessment) {

        if (riskAssessment.getLoanRequest() != null &&
            riskAssessment.getLoanRequest().getId() == null) {

            riskAssessment.setLoanRequest(
                loanRequestRepository.save(riskAssessment.getLoanRequest())
            );
        }

        return riskAssessmentRepository.save(riskAssessment);
    }

    @Override
    public Optional<RiskAssessment> getRiskAssessmentByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequest_Id(loanRequestId);
    }
}

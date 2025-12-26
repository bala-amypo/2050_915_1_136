package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRequestRepository,
            FinancialProfileRepository financialProfileRepository,
            RiskAssessmentRepository riskAssessmentRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    @Override
    public RiskAssessment logAssessment(RiskAssessment log) {
        return riskAssessmentRepository.save(log);
    }

    @Override
    public List<RiskAssessment> getLogsByRequest(Long requestId) {
        return riskAssessmentRepository.findByLoanRequestId(requestId);
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {
        RiskAssessment assessment = new RiskAssessment();
        assessment.setLoanRequestId(loanRequestId);
        assessment.setRiskScore(50);
        assessment.setCreditCheckStatus("OK");
        return assessment;
    }

    @Override
    public Optional<RiskAssessment> getByLoanRequestId(Long loanRequestId) {
        return Optional.empty();
    }
}

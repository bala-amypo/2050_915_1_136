package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

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
public RiskAssessment assessRisk(Long userId) {
    RiskAssessment assessment = new RiskAssessment();
    assessment.setUserId(userId); 
    assessment.setRiskScore(50);
    assessment.setCreditCheckStatus("OK");

    return riskAssessmentRepository.save(assessment);
}


    @Override
public List<RiskAssessment> getLogsByRequest(Long userId) {
    return riskAssessmentRepository.findByUserId(userId);
}

@Override
public RiskAssessment getByUserId(Long userId) {
    List<RiskAssessment> list = riskAssessmentRepository.findByUserId(userId);
    return list.isEmpty() ? null : list.get(0);
}

}
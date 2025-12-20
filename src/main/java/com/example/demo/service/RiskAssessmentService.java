package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;

import java.util.Optional;

public interface RiskAssessmentService {

    RiskAssessment saveRiskAssessment(RiskAssessment riskAssessment);

    Optional<RiskAssessment> getRiskAssessmentByLoanRequestId(Long loanRequestId);
}

package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.Optional;

public interface RiskAssessmentService {

    // Save a risk assessment linked to a loan request
    RiskAssessment saveRiskAssessment(Long loanRequestId, RiskAssessment riskAssessment);

    // Get a risk assessment by loan request ID
    Optional<RiskAssessment> getRiskAssessmentByLoanRequestId(Long loanRequestId);
}

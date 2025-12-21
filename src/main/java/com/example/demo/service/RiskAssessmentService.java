package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.Optional;

public interface RiskAssessmentService {
    Optional<RiskAssessment> getByLoanRequestId(Long loanRequestId);
}

package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByRequest(Long requestId);

    RiskAssessment assessRisk(Long loanRequestId);

    // ⚠ Return type should match implementation (single RiskAssessment)
    RiskAssessment getByLoanRequestId(Long loanRequestId);
}

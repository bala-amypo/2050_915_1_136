package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;

import java.util.List;
import java.util.Optional;

public interface RiskAssessmentService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByRequest(Long requestId);

    RiskAssessment assessRisk(Long loanRequestId);

        RiskAssessment getByLoanRequestId(Long loanRequestId);
}

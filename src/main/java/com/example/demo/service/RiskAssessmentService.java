package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;

import java.util.List;

public interface RiskAssessmentService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByUser(Long userId); // test expects getLogsByUser

    RiskAssessment assessRisk(Long userId);

    RiskAssessment getLatestByUser(Long userId); // test expects getLatestByUser

    RiskAssessment getByLoanRequestId(Long loanRequestId);
}

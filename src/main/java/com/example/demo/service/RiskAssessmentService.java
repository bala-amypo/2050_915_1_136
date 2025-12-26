package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByUser(Long userId);

    RiskAssessment assessRisk(Long loanRequestId);

    RiskAssessment getLatestByUser(Long userId);
}

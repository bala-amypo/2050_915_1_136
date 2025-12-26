package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByRequest(Long userId);

    RiskAssessment assessRisk(Long userId);

    RiskAssessment getByUserId(Long userId);
}

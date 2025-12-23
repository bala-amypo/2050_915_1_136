package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentLogService {

    RiskAssessment logAssessment(RiskAssessment log);

    List<RiskAssessment> getLogsByRequest(Long requestId);
}

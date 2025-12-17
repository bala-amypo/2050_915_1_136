package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import java.util.List;
import java.util.Optional;

public interface RiskAssessmentLogService {

    RiskAssessmentLog createLog(RiskAssessmentLog log);

    RiskAssessmentLog updateLog(Long id, RiskAssessmentLog log);

    Optional<RiskAssessmentLog> getLogById(Long id);

    List<RiskAssessmentLog> getAllLogs();

    boolean deleteLog(Long id);
}

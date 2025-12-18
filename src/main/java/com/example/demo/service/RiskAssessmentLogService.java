package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import java.util.List;
import java.util.Optional;

public interface RiskAssessmentLogService {

    RiskAssessmentLog createLog(RiskAssessmentLog log);

    

    List<RiskAssessmentLog> getAllLogs();

    
}

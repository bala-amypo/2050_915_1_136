package com.example.demo.service.impl;

import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    @Override
    public String assessRisk(String userId) {
        // Dummy implementation; replace with actual logic
        return "Risk assessed for user: " + userId;
    }
}

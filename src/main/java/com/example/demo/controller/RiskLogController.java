package com.example.demo.controller;

import com.example.demo.service.RiskAssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    // Constructor injection (recommended)
    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    // Example endpoint
    @GetMapping("/risk/{userId}")
    public String getRisk(@PathVariable String userId) {
        return riskAssessmentService.assessRisk(userId);
    }
}

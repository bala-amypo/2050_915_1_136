package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/risk-assessments")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    // Create / save a risk assessment for a loan request
    @PostMapping("/{loanRequestId}")
    public ResponseEntity<RiskAssessment> saveRiskAssessment(
            @PathVariable Long loanRequestId,
            @RequestBody RiskAssessment riskAssessment) {

        RiskAssessment savedRiskAssessment = riskAssessmentService.saveRiskAssessment(loanRequestId, riskAssessment);
        return ResponseEntity.ok(savedRiskAssessment);
    }
    }
package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @PostMapping("/save/{loanRequestId}")
    public ResponseEntity<RiskAssessment> saveRiskAssessment(
            @PathVariable Long loanRequestId,
            @RequestBody RiskAssessment riskAssessment) {

        RiskAssessment saved =
                riskAssessmentService.saveRiskAssessment(loanRequestId, riskAssessment);

        return ResponseEntity.ok(saved);
    }
}

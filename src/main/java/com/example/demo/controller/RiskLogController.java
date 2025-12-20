package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
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

    @PostMapping("/save")
    public ResponseEntity<RiskAssessment> saveRiskAssessment(@RequestBody RiskAssessment riskAssessment) {
        RiskAssessment saved = riskAssessmentService.saveRiskAssessment(riskAssessment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/loan/{loanRequestId}")
    public ResponseEntity<RiskAssessment> getRiskByLoanRequest(@PathVariable Long loanRequestId) {
        return riskAssessmentService.getRiskAssessmentByLoanRequestId(loanRequestId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

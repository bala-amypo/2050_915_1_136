package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-assessments")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    // GET /api/risk-assessments/{loanRequestId}
    @GetMapping("/{loanRequestId}")
    public ResponseEntity<List<RiskAssessment>> getByLoanRequestId(
            @PathVariable Long loanRequestId) {

        List<RiskAssessment> list =
                riskAssessmentService.getByLoanRequestId(loanRequestId);

        return ResponseEntity.ok(list);
    }
}

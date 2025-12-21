package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @PostMapping("/risk/{loanRequestId}")
    public ResponseEntity<RiskAssessment> calculateRisk(
            @PathVariable Long loanRequestId,
            @RequestBody RiskAssessment riskAssessment) {

        return ResponseEntity.ok(
                riskAssessmentService.saveRiskAssessment(loanRequestId, riskAssessment)
        );
    }
}

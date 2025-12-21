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

   @PostMapping("/risk-assessments")
public ResponseEntity<RiskAssessment> createRiskAssessment(@RequestBody RiskAssessment riskAssessment) {
    Long loanRequestId = riskAssessment.getLoanRequest().getId();

    // Fetch the existing LoanRequest
    LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
        .orElseThrow(() -> new ResourceNotFoundException("LoanRequest not found with id: " + loanRequestId));

    riskAssessment.setLoanRequest(loanRequest); // associate the existing loan request
    riskAssessment.setCreatedAt(LocalDateTime.now());

    RiskAssessment saved = riskAssessmentRepository.save(riskAssessment);
    return ResponseEntity.ok(saved);
}
}

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
@RequestMapping("/api/risk-assessments")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;
    private final LoanRequestRepository loanRequestRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskLogController(RiskAssessmentService riskAssessmentService,
                             LoanRequestRepository loanRequestRepository,
                             RiskAssessmentRepository riskAssessmentRepository) {
        this.riskAssessmentService = riskAssessmentService;
        this.loanRequestRepository = loanRequestRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    @PostMapping
    public ResponseEntity<RiskAssessment> createRiskAssessment(@RequestBody RiskAssessment riskAssessment) {
        Long loanRequestId = riskAssessment.getLoanRequest().getId();

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanRequest not found with id: " + loanRequestId));

        riskAssessment.setLoanRequest(loanRequest);
        riskAssessment.setCreatedAt(LocalDateTime.now());

        RiskAssessment saved = riskAssessmentRepository.save(riskAssessment);
        return ResponseEntity.ok(saved);
    }
}

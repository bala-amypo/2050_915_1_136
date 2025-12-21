package com.example.demo.controller;

import com.example.demo.service.RiskAssessmentService;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/risk-assessments")
public class RiskLogController {

    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskLogController(RiskAssessmentRepository riskAssessmentRepository) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

   @GetMapping("/{loanRequestId}")
public ResponseEntity<?> getByLoanRequestId(@PathVariable Long loanRequestId) {

    return riskAssessmentService.getByLoanRequestId(loanRequestId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No Risk Assessment found for LoanRequest ID: " + loanRequestId));
}

}

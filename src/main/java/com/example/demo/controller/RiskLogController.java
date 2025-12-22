package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/risk-logs")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @GetMapping("/loan/{loanRequestId}")
    public ResponseEntity<RiskAssessment> getByLoanRequestId(
            @PathVariable Long loanRequestId) {

        Optional<RiskAssessment> riskAssessment =
                riskAssessmentService.getByLoanRequestId(loanRequestId);

        return riskAssessment
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

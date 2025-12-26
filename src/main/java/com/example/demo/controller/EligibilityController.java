package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    // Evaluate eligibility (creates a new result)
    @PostMapping("/evaluate/{loanRequestId}")
    public ResponseEntity<EligibilityResult> evaluateEligibility(@PathVariable Long loanRequestId) {
        EligibilityResult result = eligibilityService.evaluateEligibility(loanRequestId);
        return ResponseEntity.ok(result);
    }

    // Retrieve eligibility result (does NOT create a new one)
    @GetMapping("/{loanRequestId}")
    public ResponseEntity<EligibilityResult> getEligibility(@PathVariable Long loanRequestId) {
        EligibilityResult result = eligibilityService.getByLoanRequestId(loanRequestId);
        return ResponseEntity.ok(result);
    }
}

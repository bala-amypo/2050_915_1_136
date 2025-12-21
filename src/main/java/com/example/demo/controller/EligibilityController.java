package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService service;

    public EligibilityController(EligibilityService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{loanRequestId}")
    public ResponseEntity<EligibilityResult> evaluate(
            @PathVariable Long loanRequestId) {
        return ResponseEntity.ok(service.evaluateEligibility(loanRequestId));
    }


    // ✅ ADD THIS METHOD
    // GET /api/eligibility/result/{loanRequestId}
    @GetMapping("/result/{loanRequestId}")
    public ResponseEntity<String> getEligibilityResult(
            @PathVariable Long loanRequestId) {

        String result = eligibilityService.getEligibilityResult(loanRequestId);
        return ResponseEntity.ok(result);
    }
}

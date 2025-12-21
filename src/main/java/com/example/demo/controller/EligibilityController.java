package com.example.demo.controller;

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

    // POST → Evaluate eligibility and save result
    @PostMapping("/evaluate/{loanRequestId}")
    public ResponseEntity<?> evaluate(@PathVariable Long loanRequestId) {
        return ResponseEntity.ok(service.evaluateEligibility(loanRequestId));
    }

}
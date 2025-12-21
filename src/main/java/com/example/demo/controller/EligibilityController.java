package com.example.demo.controller;

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

    // POST /api/eligibility/check
    @PostMapping("/check")
    public ResponseEntity<String> checkEligibility(@RequestBody Long loanRequestId) {
        boolean eligible = eligibilityService.checkEligibility(loanRequestId);
        return ResponseEntity.ok(eligible ? "Eligible" : "Not Eligible");
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

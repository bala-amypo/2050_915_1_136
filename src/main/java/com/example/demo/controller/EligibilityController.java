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

    /**
     * POST: Evaluate eligibility for a loan request
     * REQUIRED by Swagger + integration flow
     */
    @PostMapping("/evaluate/{loanRequestId}")
    public ResponseEntity<EligibilityResult> evaluateEligibility(
            @PathVariable Long loanRequestId) {

        EligibilityResult result =
                eligibilityService.evaluateEligibility(loanRequestId);

        return ResponseEntity.ok(result);
    }

    /**
     * GET: Fetch eligibility result by loanRequestId
     * REQUIRED by HQL test t48
     */
    @GetMapping("/{loanRequestId}")
    public ResponseEntity<EligibilityResult> getEligibility(
            @PathVariable Long loanRequestId) {

        EligibilityResult result =
                eligibilityService.getByLoanRequestId(loanRequestId);

        return ResponseEntity.ok(result);
    }
}

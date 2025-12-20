package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.EligibilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/check/{loanRequestId}")
    public EligibilityResult checkEligibility(@PathVariable Long loanRequestId) {
        // For demo, creating a dummy LoanRequest.
        // In real app, fetch from LoanRequestRepository
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setId(loanRequestId);
        loanRequest.setAmount(50000); // example value

        return eligibilityService.checkEligibility(loanRequest);
    }
}

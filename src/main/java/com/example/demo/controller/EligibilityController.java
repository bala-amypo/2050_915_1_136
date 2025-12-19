package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityService service;

    public EligibilityController(EligibilityService service) {
        this.service = service;
    }

    @PostMapping("/{loanRequestId}")
    public ResponseEntity<EligibilityResult> check(@PathVariable Long loanRequestId) {
        return ResponseEntity.ok(service.checkEligibility(loanRequestId));
    }
}

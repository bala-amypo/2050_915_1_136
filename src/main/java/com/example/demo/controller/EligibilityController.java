package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @PostMapping("/{loanRequestId}")
    public ResponseEntity<Map<String, Object>> checkEligibility(
            @PathVariable Long loanRequestId) {

        EligibilityResult result =
                eligibilityService.checkEligibility(loanRequestId);

        Map<String, Object> response = new HashMap<>();
        response.put("eligible", result.getEligible());
        response.put("disposableIncome", result.getDisposableIncome());

        return ResponseEntity.ok(response);
    }
}

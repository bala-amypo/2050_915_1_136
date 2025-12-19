package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.impl.EligibilityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityServiceImpl eligibilityService;

    public EligibilityController(EligibilityServiceImpl eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @PostMapping("/{loanRequestId}")
    public ResponseEntity<ModelMap> checkEligibility(
            @PathVariable Long loanRequestId) {

        EligibilityResult result =
                eligibilityService.evaluateEligibility(loanRequestId);

        ModelMap response = new ModelMap();
        response.addAttribute("eligible", result.getEligible());
        response.addAttribute("disposableIncome", result.getDisposableIncome());

        return ResponseEntity.ok(response);
    }
}

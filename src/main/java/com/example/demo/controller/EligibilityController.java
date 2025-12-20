package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @PostMapping("/check")
    public String checkEligibility(@RequestBody LoanRequest loanRequest) {
        boolean eligible = eligibilityService.checkEligibility(loanRequest); // use interface method
        return eligible ? "Eligible" : "Not Eligible";
    }
}

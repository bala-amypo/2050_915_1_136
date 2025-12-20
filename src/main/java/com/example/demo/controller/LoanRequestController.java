package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    public LoanRequestController(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    @PostMapping("/create")
    public LoanRequest createLoan(@RequestBody LoanRequest request) {
        return loanRequestService.saveLoanRequest(request);
    }
}

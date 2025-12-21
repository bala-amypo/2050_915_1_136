package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    public LoanRequestController(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<LoanRequest> createLoan(
            @RequestBody LoanDtos.LoanRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanRequestService.createLoanRequest(dto));
    }
}

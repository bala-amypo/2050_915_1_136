package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    public LoanRequestController(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    // ✅ POST /api/loan-requests
    @PostMapping
    public ResponseEntity<LoanRequest> createLoan(
            @RequestBody LoanDtos.LoanRequestDto dto) {

        LoanRequest created = loanRequestService.createLoanRequest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ✅ GET /api/loan-requests/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getLoansByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                loanRequestService.getByUserId(userId)
        );
    }

    // ✅ GET /api/loan-requests/{id}
    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getLoanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                loanRequestService.getById(id)
        );
    }

    // ✅ GET /api/loan-requests
    @GetMapping
    public ResponseEntity<List<LoanRequest>> getAllLoans() {

        return ResponseEntity.ok(
                loanRequestService.getAll()
        );
    }
}

package com.example.demo.controller;
im
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

    // POST /api/loan-requests
    @PostMapping("/loan-requests")
public ResponseEntity<LoanRequest> createLoanRequest(
        @RequestBody LoanRequest loanRequest) {

    LoanRequest savedLoan = loanRequestRepository.save(loanRequest);

    // 🔥 ADD THIS LINE (ELIGIBILITY CALCULATION)
    String eligibility = eligibilityService.checkEligibility(savedLoan.getId());

    savedLoan.setEligibilityResult(eligibility);
    loanRequestRepository.save(savedLoan);

    return ResponseEntity.ok(savedLoan);
}


    // GET /api/loan-requests/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                loanRequestService.getByUserId(userId)
        );
    }

    // GET /api/loan-requests/{id}
    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                loanRequestService.getById(id)
        );
    }

    // GET /api/loan-requests
    @GetMapping
    public ResponseEntity<List<LoanRequest>> getAll() {

        return ResponseEntity.ok(
                loanRequestService.getAll()
        );
    }
}

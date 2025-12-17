package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan-requests")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;

    // CREATE
    @PostMapping
    public ResponseEntity<LoanRequest> createLoanRequest(@RequestBody LoanRequest loanRequest) {
        LoanRequest created = loanRequestService.createLoanRequest(loanRequest);
        return ResponseEntity.ok(created);
    }

    // READ - all
    @GetMapping
    public ResponseEntity<List<LoanRequest>> getAllLoanRequests() {
        List<LoanRequest> list = loanRequestService.getAllLoanRequests();
        return ResponseEntity.ok(list);
    }

    // READ - by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getLoanRequestById(@PathVariable Long id) {
        Optional<LoanRequest> loanRequest = loanRequestService.getLoanRequestById(id);
        return loanRequest.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LoanRequest> updateLoanRequest(
            @PathVariable Long id,
            @RequestBody LoanRequest loanRequest) {
        try {
            LoanRequest updated = loanRequestService.updateLoanRequest(id, loanRequest);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanRequest(@PathVariable Long id) {
        boolean deleted = loanRequestService.deleteLoanRequest(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

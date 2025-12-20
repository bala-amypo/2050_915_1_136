package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

public interface LoanRequestService {
    LoanRequest saveLoanRequest(LoanRequest request);
    LoanRequest getLoanRequestById(Long id);
    // add other methods as needed
}

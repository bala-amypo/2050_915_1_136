package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import java.util.List;
import java.util.Optional;

public interface LoanRequestService {

    LoanRequest createLoanRequest(LoanRequest loanRequest);

    LoanRequest updateLoanRequest(Long id, LoanRequest loanRequest);

    Optional<LoanRequest> getLoanRequestById(Long id);

    List<LoanRequest> getAllLoanRequests();

    boolean deleteLoanRequest(Long id);
}

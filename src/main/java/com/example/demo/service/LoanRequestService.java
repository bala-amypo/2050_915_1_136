package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import java.util.List;
import java.util.Optional;

public interface LoanRequestService {

    LoanRequest createLoanRequest(LoanRequest loanRequest);


    List<LoanRequest> getAllLoanRequests();

  
}

package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private LoanRequestRepository repository;

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        return repository.save(loanRequest);
    }

   
    @Override
    public List<LoanRequest> getAllLoanRequests() {
        return repository.findAll();
    }

   
}

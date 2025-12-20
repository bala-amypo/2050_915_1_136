package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl {

    private final LoanRequestRepository repo;

    public LoanRequestServiceImpl(LoanRequestRepository repo) {
        this.repo = repo;
    }

    public List<LoanRequest> getRequestsByUser(long userId) {
        return repo.findByUserId(userId);
    }

    public LoanRequest getById(long id) {
        return repo.findById(id).orElseThrow();
    }
}

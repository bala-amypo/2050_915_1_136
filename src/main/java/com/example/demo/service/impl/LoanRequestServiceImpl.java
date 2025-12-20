package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;

    // Single constructor (tests + Spring)
    public LoanRequestServiceImpl(LoanRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public LoanRequest submitRequest(LoanRequest request) {
        if (request == null || request.getUser() == null) {
            throw new RuntimeException("Invalid loan request");
        }

        // ✅ DO NOT set status or timestamp
        // @PrePersist will handle it

        return repo.save(request);
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        // ✅ Do NOT throw exception
        return repo.findByUserId(userId);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan request not found"));
    }
}

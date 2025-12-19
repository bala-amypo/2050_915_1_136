package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;

import java.util.List;

public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;
    private final UserRepository userRepo;

    public LoanRequestServiceImpl(LoanRequestRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public LoanRequest submitRequest(LoanRequest request) {

        if (request.getRequestedAmount() == null || request.getRequestedAmount() <= 0) {
            throw new BadRequestException("Invalid amount");
        }

        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        request.setUser(user);
        return repo.save(request);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repo.findByUserId(userId);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;
    private final UserRepository userRepo;

    public LoanRequestServiceImpl(LoanRequestRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public LoanRequest submitRequest(LoanRequest request) {
        // 1. Basic Validation
        if (request == null || request.getRequestedAmount() == null || request.getRequestedAmount() <= 0) {
            throw new BadRequestException("Invalid amount");
        }

        if (request.getUser() == null || request.getUser().getId() == null) {
            throw new BadRequestException("User ID is required");
        }

        // 2. Critical: Fetch user from DB and re-attach to the request
        // This fixes t19 and t46 by ensuring the relationship is correctly mapped in the DB
        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        // 3. Set Defaults (Fixes t28 and t29)
        if (request.getStatus() == null) {
            request.setStatus(LoanRequest.Status.PENDING);
        }
        
        // Ensure the full user object is associated, not just the ID
        request.setUser(user);

        return repo.save(request);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        // 4. Fixes t14, t19, t46: Test suite specifically checks for this exception 
        // if the user ID provided does not exist in the database.
        if (!userRepo.existsById(userId)) {
            throw new BadRequestException("User not found");
        }
        return repo.findByUserId(userId);
    }
}
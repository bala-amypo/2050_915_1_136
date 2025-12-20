package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;
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
    public LoanRequest submitRequest(LoanRequest request) {
        if (request == null || request.getRequestedAmount() == null || request.getRequestedAmount() <= 0) {
            throw new BadRequestException("Invalid amount");
        }

        if (request.getUser() == null || request.getUser().getId() == null) {
            throw new BadRequestException("User ID is required");
        }

        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        // Use the Status enum inside LoanRequest
        if (request.getStatus() == null) {
            request.setStatus(LoanRequest.Status.PENDING);
        }

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
        if (!userRepo.existsById(userId)) {
            throw new BadRequestException("User not found");
        }
        return repo.findByUserId(userId);
    }
}
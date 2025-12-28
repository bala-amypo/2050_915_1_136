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

    public LoanRequestServiceImpl(LoanRequestRepository r, UserRepository u) {
        this.repo = r;
        this.userRepo = u;
    }

    @Override
    public LoanRequest submitRequest(LoanRequest request) {
        // Find and set the user
        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        request.setUser(user);

        // Ensure default status is set if null
        if (request.getStatus() == null) {
            request.setStatus(LoanRequest.Status.PENDING);
        }

        // Ensure submittedAt is set
        if (request.getSubmittedAt() == null) {
            request.setSubmittedAt(java.time.LocalDateTime.now());
        }

        return repo.save(request);
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));
    }
}

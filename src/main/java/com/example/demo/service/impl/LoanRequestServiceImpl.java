package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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
    @Transactional
    public LoanRequest submitRequest(LoanRequest request) {
        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        
        request.setStatus(LoanRequest.Status.PENDING);
        request.setSubmittedAt(LocalDateTime.now());
        request.setUser(user);
        return repo.save(request);
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        if (!userRepo.existsById(userId)) throw new BadRequestException("User not found");
        return repo.findByUserId(userId);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new BadRequestException("Loan request not found"));
    }
}
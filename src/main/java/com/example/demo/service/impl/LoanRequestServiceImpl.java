package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private LoanRequestRepository repo;
    private UserRepository userRepo;

    // ✅ REQUIRED by Spring (NO-ARG constructor)
    public LoanRequestServiceImpl() {
    }

    // ✅ Used by tests & Spring injection
    public LoanRequestServiceImpl(LoanRequestRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public LoanRequest submitRequest(LoanRequest request) {

        User user = userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        request.setUser(user);
        request.setStatus(LoanRequest.Status.PENDING);
        request.setSubmittedAt(LocalDateTime.now());

        return repo.save(request);
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new BadRequestException("User not found");
        }
        return repo.findByUserId(userId);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));
    }
}

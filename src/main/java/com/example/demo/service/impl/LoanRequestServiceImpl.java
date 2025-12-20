package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRepo;
    private final UserRepository userRepo;

    public LoanRequestServiceImpl(LoanRequestRepository loanRepo,
                                  UserRepository userRepo) {
        this.loanRepo = loanRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LoanRequest submitRequest(LoanRequest request) {
        userRepo.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return loanRepo.save(request);
    }

    @Override
    public List<LoanRequest> getByUserId(Long userId) {
        return loanRepo.findByUserId(userId);
    }
}

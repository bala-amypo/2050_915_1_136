package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // <- This makes it a Spring Bean
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;

    public LoanRequestServiceImpl(LoanRequestRepository loanRequestRepository) {
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    public LoanRequest saveLoanRequest(LoanRequest request) {
        return loanRequestRepository.save(request);
    }

    @Override
    public LoanRequest getLoanRequestById(Long id) {
        Optional<LoanRequest> opt = loanRequestRepository.findById(id);
        return opt.orElse(null);
    }
}

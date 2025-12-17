package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private LoanRequestRepository repository;

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        return repository.save(loanRequest);
    }

    @Override
    public LoanRequest updateLoanRequest(Long id, LoanRequest loanRequest) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setRequestedAmount(loanRequest.getRequestedAmount());
                    existing.setTenureMonths(loanRequest.getTenureMonths());
                    existing.setPurpose(loanRequest.getPurpose());
                    existing.setStatus(loanRequest.getStatus());
                    existing.setUser(loanRequest.getUser());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("LoanRequest not found with id " + id));
    }

    @Override
    public Optional<LoanRequest> getLoanRequestById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<LoanRequest> getAllLoanRequests() {
        return repository.findAll();
    }

    @Override
    public boolean deleteLoanRequest(Long id) {
        return repository.findById(id)
                .map(request -> {
                    repository.delete(request);
                    return true;
                })
                .orElse(false);
    }
}

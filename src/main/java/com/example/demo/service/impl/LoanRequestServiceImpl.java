package com.example.demo.service.impl;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;
    private final UserRepository userRepository;

    public LoanRequestServiceImpl(
            LoanRequestRepository loanRequestRepository,
            UserRepository userRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
    }

    // ✅ POST – create loan request
    @Override
    public LoanRequest createLoanRequest(LoanDtos.LoanRequestDto dto) {

        LoanRequest loan = new LoanRequest();
        loan.setRequestedAmount(dto.getRequestedAmount());
        loan.setTenureMonths(dto.getTenureMonths());

        loan.setUser(
                userRepository.findById(dto.getUserId())
                        .orElseThrow(() ->
                                new RuntimeException("User not found"))
        );

        loan.setStatus("PENDING");

        return loanRequestRepository.save(loan);
    }

    // ✅ GET – by user
    @Override
    public List<LoanRequest> getByUserId(Long userId) {
        return loanRequestRepository.findByUserId(userId);
    }

    // ✅ GET – by id
    @Override
    public LoanRequest getById(Long id) {
        return loanRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loan request not found"));
    }

    // ✅ GET – all
    @Override
    public List<LoanRequest> getAll() {
        return loanRequestRepository.findAll();
    }
}

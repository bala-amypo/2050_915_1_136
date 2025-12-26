package com.example.demo.service.impl;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public LoanRequest createLoanRequest(LoanDtos.LoanRequestDto dto) {
        if (dto == null || dto.getUserId() == null) {
            throw new BadRequestException("Invalid loan request data");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        LoanRequest loan = new LoanRequest();
        loan.setRequestedAmount(dto.getRequestedAmount());
        loan.setTenureMonths(dto.getTenureMonths());
        loan.setStatus(LoanRequest.Status.PENDING);
        loan.setSubmittedAt(LocalDateTime.now());
        loan.setUser(user);

        return loanRequestRepository.save(loan);
    }

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        if (loanRequest == null || loanRequest.getUser() == null) {
            throw new BadRequestException("Invalid loan request");
        }
        loanRequest.setStatus(LoanRequest.Status.PENDING);
        loanRequest.setSubmittedAt(LocalDateTime.now());
        return loanRequestRepository.save(loanRequest);
    }

    @Override
    public List<LoanRequest> getByUserId(Long userId) {
        return loanRequestRepository.findByUserId(userId);
    }

    @Override
    public LoanRequest getById(Long id) {
        return loanRequestRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));
    }

    @Override
    public List<LoanRequest> getAll() {
        return loanRequestRepository.findAll();
    }
}

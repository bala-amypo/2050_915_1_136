package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest createLoanRequest(LoanDtos.LoanRequestDto dto);

    List<LoanRequest> getLoanRequestsByUserId(Long userId);
}

package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest submitRequest(LoanDtos.LoanRequestDto dto);

    LoanRequest submitRequest(LoanRequest loanRequest);

    List<LoanRequest> getRequestsByUser(Long userId);

    LoanRequest getById(Long id);

    List<LoanRequest> getAll();
}

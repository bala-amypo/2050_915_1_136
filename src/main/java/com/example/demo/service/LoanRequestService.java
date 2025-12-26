package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest submitRequest(LoanDtos.LoanRequestDto dto);

    List<LoanRequest> getRequestsByUser(Long userId);

    LoanRequest getRequestById(Long id);
    
    List<LoanRequest> getAllRequests();
}

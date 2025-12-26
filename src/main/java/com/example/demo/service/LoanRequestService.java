package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest submitRequest(LoanDtos.LoanRequestDto dto); // test expects submitRequest

    List<LoanRequest> getRequestsByUser(Long userId); // test expects getRequestsByUser

    LoanRequest getRequestById(Long id); // test expects getRequestById

    List<LoanRequest> getAllRequests(); // test expects getAllRequests
}

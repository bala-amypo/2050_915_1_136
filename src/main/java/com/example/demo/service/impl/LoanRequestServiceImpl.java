package com.example.demo.service.impl;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;

    public LoanRequestServiceImpl(LoanRequestRepository repo) {
        this.repo = repo;
    }

   @Override
public LoanRequest submitRequest(LoanDtos.LoanRequestDto dto) {
    LoanRequest request = new LoanRequest();
    request.setRequestedAmount(dto.getRequestedAmount());
    request.setTenureMonths(dto.getTenureMonths());

    // Set the User entity
    User user = new User();
    user.setId(dto.getUserId()); // set only ID, JPA will handle it
    request.setUser(user);

    return repo.save(request);
}


    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public LoanRequest getRequestById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<LoanRequest> getAllRequests() {
        return repo.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RiskAssessment> getByLoanRequestId(Long loanRequestId) {
        List<RiskAssessment> list = repository.findByLoanRequestId(loanRequestId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No Risk Assessment found for LoanRequest ID: " + loanRequestId
            );
        }
        return list;
    }
}

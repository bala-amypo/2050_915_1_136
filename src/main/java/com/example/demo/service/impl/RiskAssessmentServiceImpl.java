package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repo;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public RiskAssessment logAssessment(RiskAssessment log) {
        return repo.save(log);
    }

    @Override
    public List<RiskAssessment> getLogsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public RiskAssessment getLatestByUser(Long userId) {
        List<RiskAssessment> logs = repo.findByUserId(userId);
        return logs.isEmpty() ? null : logs.get(logs.size() - 1);
    }

    @Override
    public RiskAssessment assessRisk(Long userId) {
        // Implement risk assessment logic
        return null;
    }

    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return repo.findByLoanRequestId(loanRequestId).orElse(null);
    }
}

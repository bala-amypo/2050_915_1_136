package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    @Autowired
    private RiskAssessmentLogRepository repository;

    @Override
    public RiskAssessmentLog createLog(RiskAssessmentLog log) {
        return repository.save(log);
    }

    @Override
    public RiskAssessmentLog updateLog(Long id, RiskAssessmentLog log) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setLoanRequestId(log.getLoanRequestId());
                    existing.setDtiRatio(log.getDtiRatio());
                    existing.setCreditCheckStatus(log.getCreditCheckStatus());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("RiskAssessmentLog not found with id " + id));
    }

    @Override
    public Optional<RiskAssessmentLog> getLogById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RiskAssessmentLog> getAllLogs() {
        return repository.findAll();
    }

    @Override
    public boolean deleteLog(Long id) {
        return repository.findById(id)
                .map(log -> {
                    repository.delete(log);
                    return true;
                })
                .orElse(false);
    }
}

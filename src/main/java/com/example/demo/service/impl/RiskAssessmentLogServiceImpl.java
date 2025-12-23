package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository logRepository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public RiskAssessment logAssessment(RiskAssessment log) {
        return logRepository.save(log);
    }

    @Override
    public List<RiskAssessmentLo> getLogsByRequest(Long requestId) {
        return logRepository.findByLoanRequestId(requestId);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRequestRepository,
            FinancialProfileRepository financialProfileRepository,
            RiskAssessmentRepository riskAssessmentRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    @Override
    public RiskAssessment logAssessment(RiskAssessment log) {
        if (log == null) throw new BadRequestException("Invalid risk log");
        return riskAssessmentRepository.save(log);
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {
        LoanRequest request = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        // Check if risk already assessed
        Optional<RiskAssessment> existing = riskAssessmentRepository.findByLoanRequestId(loanRequestId);
        if (existing.isPresent()) {
            throw new BadRequestException("Risk assessment already exists for this loan request");
        }

        FinancialProfile profile = financialProfileRepository.findByUserId(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        RiskAssessment assessment = new RiskAssessment();
        assessment.setLoanRequest(request);
        assessment.setUserId(request.getUser().getId());

        // Compute DTI ratio and simple risk score
        double dtiRatio = profile.getExistingLoanEmi() / Math.max(profile.getMonthlyIncome(), 1);
        assessment.setDtiRatio(dtiRatio);

        double score = 100 - (dtiRatio * 100);
        score = Math.max(0.0, Math.min(score, 100.0));
        assessment.setRiskScore(score);

        assessment.setCreditCheckStatus(profile.getCreditScore() >= 650 ? "OK" : "REVIEW");
        assessment.setCreatedAt(LocalDateTime.now());

        return riskAssessmentRepository.save(assessment);
    }

    @Override
    public List<RiskAssessment> getLogsByRequest(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId).map(List::of).orElse(List.of());
    }

    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId)
                .orElse(null);
    }
}

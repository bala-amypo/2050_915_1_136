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
import java.util.List;

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
    public List<RiskAssessment> getLogsByUser(Long userId) {
        return riskAssessmentRepository.findByUserId(userId);
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {
        LoanRequest request = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        FinancialProfile profile = financialProfileRepository.findTopByUserIdOrderByCreatedAtDesc(request.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        RiskAssessment assessment = new RiskAssessment();
        assessment.setUserId(request.getUser().getId());
        assessment.setLoanRequestId(loanRequestId);

        double dtiRatio = profile.getExistingLoanEmi() / Math.max(profile.getMonthlyIncome(), 1);
        assessment.setDtiRatio(dtiRatio);

        int score = (int)Math.max(0, Math.min(100, 100 - (dtiRatio * 100)));
        assessment.setRiskScore(score);

        assessment.setCreditCheckStatus(profile.getCreditScore() != null && profile.getCreditScore() >= 650 ? "OK" : "REVIEW");

        return riskAssessmentRepository.save(assessment);
    }

    @Override
    public RiskAssessment getLatestByUser(Long userId) {
        return riskAssessmentRepository.findTopByUserIdOrderByTimestampDesc(userId)
                .orElse(null);
    }
}

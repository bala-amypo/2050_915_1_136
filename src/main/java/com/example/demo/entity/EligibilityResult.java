package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_result")
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One EligibilityResult per LoanRequest
    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;

    @Column(name = "is_eligible", nullable = false)
    private boolean eligible;

    @Column(name = "max_eligible_amount", nullable = false)
    private Double maxEligibleAmount;

    @Column(name = "estimated_emi", nullable = false)
    private Double estimatedEmi;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "calculated_at", nullable = false)
    private LocalDateTime calculatedAt;

    // 🔹 No-args constructor
    public EligibilityResult() {}

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount;
    }

    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public Double getEstimatedEmi() {
        return estimatedEmi;
    }

    public void setEstimatedEmi(Double estimatedEmi) {
        this.estimatedEmi = estimatedEmi;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}

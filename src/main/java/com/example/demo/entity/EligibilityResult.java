package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "eligibility_result")
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;

    @Column(nullable = false)
    private Boolean isEligible;

    private Double maxEligibleAmount;

    private Double estimatedEmi;

    @Column(nullable = false)
    private String riskLevel; // LOW / MEDIUM / HIGH

    private String rejectionReason;

    @Column(nullable = false, updatable = false)
    private Timestamp calculatedAt;

    // ===== Constructors =====

    // No-args constructor (required by JPA)
    public EligibilityResult() {
    }

    // Parameterized constructor
    public EligibilityResult(LoanRequest loanRequest,
                             Boolean isEligible,
                             Double maxEligibleAmount,
                             Double estimatedEmi,
                             String riskLevel,
                             String rejectionReason) {
        this.loanRequest = loanRequest;
        this.isEligible = isEligible;
        this.maxEligibleAmount = maxEligibleAmount;
        this.estimatedEmi = estimatedEmi;
        this.riskLevel = riskLevel;
        this.rejectionReason = rejectionReason;
    }

    // ===== Auto timestamp =====
    @PrePersist
    protected void onCreate() {
        this.calculatedAt = new Timestamp(System.currentTimeMillis());
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public Boolean getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
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

    public Timestamp getCalculatedAt() {
        return calculatedAt;
    }
}

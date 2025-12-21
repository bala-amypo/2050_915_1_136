package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_results")
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * One eligibility result per loan request
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "loan_request_id",
        nullable = false,
        unique = true
    )
    private LoanRequest loanRequest;

    @Column(nullable = false)
    private Boolean isEligible;

    @Column(nullable = false)
    private Double maxEligibleAmount;

    @Column(nullable = false)
    private Double estimatedEmi;

    @Column(nullable = false)
    private String riskLevel;

    @Column(length = 255)
    private String rejectionReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime calculatedAt;

    // ===============================
    // Constructors
    // ===============================

    public EligibilityResult() {
    }

    public EligibilityResult(
            LoanRequest loanRequest,
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

    // ===============================
    // JPA Lifecycle
    // ===============================

    @PrePersist
    protected void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    // ===============================
    // Getters & Setters
    // ===============================

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

    public void setIsEligible(Boolean eligible) {
        isEligible = eligible;
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
}

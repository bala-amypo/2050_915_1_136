package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessment_logs")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to LoanRequest (audit-only, no FK)
     */
    @Column(name = "loan_request_id", nullable = false)
    private Long loanRequestId;

    /**
     * Debt-to-Income ratio
     */
    @Column(nullable = false)
    private Double dtiRatio;

    /**
     * APPROVED | REJECTED | PENDING_REVIEW
     */
    @Column(nullable = false, length = 30)
    private String creditCheckStatus;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    // ===============================
    // Constructors
    // ===============================

    public RiskAssessment() {
    }

    public RiskAssessment(
            Long loanRequestId,
            Double dtiRatio,
            String creditCheckStatus) {
        this.loanRequestId = loanRequestId;
        this.dtiRatio = dtiRatio;
        this.creditCheckStatus = creditCheckStatus;
    }

    // ===============================
    // JPA Lifecycle
    // ===============================

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }

    // ===============================
    // Getters & Setters
    // ===============================

    public Long getId() {
        return id;
    }

    public Long getLoanRequestId() {
        return loanRequestId;
    }

    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public Double getDtiRatio() {
        return dtiRatio;
    }

    public void setDtiRatio(Double dtiRatio) {
        this.dtiRatio = dtiRatio;
    }

    public String getCreditCheckStatus() {
        return creditCheckStatus;
    }

    public void setCreditCheckStatus(String creditCheckStatus) {
        this.creditCheckStatus = creditCheckStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

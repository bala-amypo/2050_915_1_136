package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Audit reference only (no FK)
    @Column(name = "loan_request_id", nullable = false)
    private Long loanRequestId;

    @Column(nullable = false)
    private Double dtiRatio;

    @Column(nullable = false)
    private String creditCheckStatus;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public RiskAssessment() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

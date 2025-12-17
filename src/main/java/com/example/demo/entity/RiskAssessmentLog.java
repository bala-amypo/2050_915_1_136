package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "risk_assessment_log")
public class RiskAssessmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long loanRequestId;

    @Column(nullable = false)
    private Double dtiRatio; 

    private String creditCheckStatus;

    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;

    public RiskAssessmentLog() {
    }
    public RiskAssessmentLog(Long loanRequestId,
                             Double totalMonthlyObligations,
                             Double monthlyIncome,
                             String creditCheckStatus) {

        if (monthlyIncome == null || monthlyIncome <= 0) {
            throw new IllegalArgumentException("Monthly income must be greater than 0");
        }

        this.loanRequestId = loanRequestId;
        this.dtiRatio = totalMonthlyObligations / monthlyIncome;
        this.creditCheckStatus = creditCheckStatus;
    }

    @PrePersist
    protected void onCreate() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }


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

    public Timestamp getTimestamp() {
        return timestamp;
    }
}

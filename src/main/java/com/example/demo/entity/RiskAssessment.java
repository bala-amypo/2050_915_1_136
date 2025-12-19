package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;
    private Double riskScore;
    private Double dtiRatio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public Double getRiskScore() { return riskScore; }
    public void setRiskScore(Double riskScore) { this.riskScore = riskScore; }

    public Double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessment_logs")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "dti_ratio", nullable = false)
    private Double dtiRatio = 0.0;

    @Column(name = "credit_check_status", nullable = false)
    private String creditCheckStatus = "PENDING";

    @Column(name = "risk_score", nullable = false)
    private Integer riskScore = 0;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public RiskAssessment() {
    }

    // ---------- JPA LIFECYCLE ----------

    @PrePersist
    protected void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
        if (this.creditCheckStatus == null) {
            this.creditCheckStatus = "PENDING";
        }
        if (this.riskScore == null) {
            this.riskScore = 0;
        }
        if (this.dtiRatio == null) {
            this.dtiRatio = 0.0;
        }
    }

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getDtiRatio() {
        return dtiRatio != null ? dtiRatio : 0.0;
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

    public Integer getRiskScore() {
        return riskScore != null ? riskScore : 0;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

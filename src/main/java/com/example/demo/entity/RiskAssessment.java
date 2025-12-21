package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    @Column(name = "risk_score")
    private int riskScore;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "assessed_at", nullable = false)
    private LocalDateTime assessedAt;

    @PrePersist
    protected void onCreate() {
        this.assessedAt = LocalDateTime.now();
    }

    // Getters and Setters ...
}
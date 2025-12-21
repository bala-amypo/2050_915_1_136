package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_result") // Ensures it maps to your MariaDB table
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    private boolean eligible;
    private String reason;

    @Column(name = "calculated_at", nullable = false)
    private LocalDateTime calculatedAt;

    // Automatically sets the timestamp before saving to database
    @PrePersist
    protected void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    // Constructors
    public EligibilityResult() {}

    public EligibilityResult(LoanRequest loanRequest, boolean eligible, String reason) {
        this.loanRequest = loanRequest;
        this.eligible = eligible;
        this.reason = reason;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }

    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
}
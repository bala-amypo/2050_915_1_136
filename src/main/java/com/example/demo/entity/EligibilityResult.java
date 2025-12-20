package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false) // Usually OneToOne for a specific LoanRequest
    private LoanRequest loanRequest;

    private Boolean eligible;
    private Double disposableIncome;
    
    // RENAMED: To match what the Service is calling in the error log
    private Double maxEmiPossible; 

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.eligible == null) {
            this.eligible = false;
        }
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }

    public Boolean getEligible() { return eligible; }
    public void setEligible(Boolean eligible) { this.eligible = eligible; }

    public Double getDisposableIncome() { return disposableIncome; }
    public void setDisposableIncome(Double disposableIncome) { this.disposableIncome = disposableIncome; }

    // FIX: Method name now matches the Service call
    public Double getMaxEmiPossible() { return maxEmiPossible; }
    public void setMaxEmiPossible(Double maxEmiPossible) { this.maxEmiPossible = maxEmiPossible; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
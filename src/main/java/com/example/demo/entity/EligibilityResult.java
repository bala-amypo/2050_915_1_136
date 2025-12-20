package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private LoanRequest loanRequest;

    @ManyToOne(optional = false)
    private User user;

    private Boolean eligible;

    private Double disposableIncome;

    private Double maxEligibleAmount;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.eligible == null) {
            this.eligible = false;
        }
        if (this.disposableIncome == null) {
            this.disposableIncome = 0.0;
        }
        if (this.maxEligibleAmount == null) {
            this.maxEligibleAmount = 0.0;
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public Double getDisposableIncome() {
        return disposableIncome;
    }

    public void setDisposableIncome(Double disposableIncome) {
        this.disposableIncome = disposableIncome;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount;
    }

    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

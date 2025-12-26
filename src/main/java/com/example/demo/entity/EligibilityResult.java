package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_results")
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    @Column(nullable = false)
    private Boolean eligible = false;

    @Column(nullable = false)
    private Double disposableIncome = 0.0;

    @Column(name = "max_eligible_amount", nullable = false)
    private Double maxEligibleAmount = 0.0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public EligibilityResult() {
    }

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.eligible == null) this.eligible = false;
        if (this.disposableIncome == null) this.disposableIncome = 0.0;
        if (this.maxEligibleAmount == null) this.maxEligibleAmount = 0.0;
    }

    // ---------- STANDARD GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public Double getDisposableIncome() {
        return disposableIncome != null ? disposableIncome : 0.0;
    }

    public void setDisposableIncome(Double disposableIncome) {
        this.disposableIncome = disposableIncome;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount != null ? maxEligibleAmount : 0.0;
    }

    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // ---------- BRIDGE METHODS (DO NOT REMOVE) ----------

    public void setMaxEmiPossible(Double maxEmiPossible) {
        this.maxEligibleAmount = maxEmiPossible;
    }

    public Double getMaxEmiPossible() {
        return getMaxEligibleAmount();
    }
}

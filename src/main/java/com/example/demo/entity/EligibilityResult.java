package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;

    private Boolean eligible;

    private Double disposableIncome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public Boolean getEligible() { return eligible; }
    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public Double getDisposableIncome() { return disposableIncome; }
    public void setDisposableIncome(Double disposableIncome) {
        this.disposableIncome = disposableIncome;
    }
}

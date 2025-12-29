
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanRequest loanRequest;

    private Boolean eligible;
    private Double disposableIncome;
    
    
    private Double maxEligibleAmount = 0.0;

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.eligible == null) this.eligible = false;
        if (this.maxEligibleAmount == null) this.maxEligibleAmount = 0.0;
    }

  

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }

    public Boolean getEligible() { return eligible; }
    public void setEligible(Boolean eligible) { this.eligible = eligible; }

    public Double getDisposableIncome() { return disposableIncome; }
    public void setDisposableIncome(Double disposableIncome) { this.disposableIncome = disposableIncome; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }


    
    public Double getMaxEligibleAmount() { 
        return maxEligibleAmount; 
    }
    public void setMaxEligibleAmount(Double maxEligibleAmount) { 
        this.maxEligibleAmount = maxEligibleAmount; 
    }

    
    public void setMaxEmiPossible(Double maxEmiPossible) { 
        this.maxEligibleAmount = maxEmiPossible; 
    }
    public Double getMaxEmiPossible() { 
        return maxEligibleAmount; 
    }
}

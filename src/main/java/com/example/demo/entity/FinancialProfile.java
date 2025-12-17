package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "financial_profile")
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Double monthlyIncome;

    private Double monthlyExpenses;

    private Double existingLoanEmi;

    @Column(nullable = false)
    private Integer creditScore;

    private Double savingsBalance;

    private Timestamp lastUpdatedAt;

    @PrePersist
    @PreUpdate
    protected void validateAndUpdateTimestamp() {
        // Rule: monthlyIncome > 0
        if (monthlyIncome == null || monthlyIncome <= 0) {
            throw new IllegalArgumentException("Monthly income must be greater than 0");
        }

        // Rule: creditScore between 300 and 900
        if (creditScore == null || creditScore < 300 || creditScore > 900) {
            throw new IllegalArgumentException("Credit score must be between 300 and 900");
        }

        // Auto-update timestamp
        this.lastUpdatedAt = new Timestamp(System.currentTimeMillis());
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(Double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public Double getExistingLoanEmi() {
        return existingLoanEmi;
    }

    public void setExistingLoanEmi(Double existingLoanEmi) {
        this.existingLoanEmi = existingLoanEmi;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(Double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public Timestamp getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}

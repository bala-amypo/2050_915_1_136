package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_profiles")
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double monthlyIncome;

    private double existingDebt;

    private int creditScore;

    

    private double monthlyExpenses;
    private double existingLoanEmi;
    private double savingsBalance;
    private java.time.LocalDateTime lastUpdatedAt;

    // Add these to your Getters/Setters section
    public double getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(double monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }

    public double getExistingLoanEmi() { return existingLoanEmi; }
    public void setExistingLoanEmi(double existingLoanEmi) { this.existingLoanEmi = existingLoanEmi; }

    public double getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(double savingsBalance) { this.savingsBalance = savingsBalance; }

    public java.time.LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(java.time.LocalDateTime lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
    }
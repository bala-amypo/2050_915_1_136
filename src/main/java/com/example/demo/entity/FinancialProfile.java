package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime lastUpdatedAt;

    // ✅ REQUIRED getter/setter (THIS FIXES THE ERROR)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getExistingDebt() {
        return existingDebt;
    }

    public void setExistingDebt(double existingDebt) {
        this.existingDebt = existingDebt;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public double getExistingLoanEmi() {
        return existingLoanEmi;
    }

    public void setExistingLoanEmi(double existingLoanEmi) {
        this.existingLoanEmi = existingLoanEmi;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "financial_profile")
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private Double monthlyIncome;

    private Double monthlyExpenses;

    private Double existingLoanEmi;

    private Integer creditScore;

    private Double savingsBalance;

    private Timestamp lastUpdatedAt;


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
    public FinancialProfile() {
    }
    public FinancialProfile(Long id,User user,Double monthlyIncome,Double monthlyExpenses,Double existingLoanEmi,Integer creditScore,Double savingsBalance) {
    this.id=id;
    this.user = user;
    this.monthlyIncome = monthlyIncome;
    this.monthlyExpenses = monthlyExpenses;
    this.existingLoanEmi = existingLoanEmi;
    this.creditScore = creditScore;
    this.savingsBalance = savingsBalance;
}

}

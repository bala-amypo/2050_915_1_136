package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FinancialProfile {
    private User user;
    private double monthlyIncome;
    private double monthlyExpenses;
    private double existingLoanEmi;
    private int creditScore;
    private double savingsBalance;

    // Add getters and setters for all fields
    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setMonthlyIncome(double income) { this.monthlyIncome = income; }
    public double getMonthlyIncome() { return monthlyIncome; }

    public void setMonthlyExpenses(double expenses) { this.monthlyExpenses = expenses; }
    public double getMonthlyExpenses() { return monthlyExpenses; }

    // Do the same for existingLoanEmi, creditScore, savingsBalance
}

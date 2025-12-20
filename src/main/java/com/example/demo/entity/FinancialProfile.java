package com.example.demo.entity;
import java.time.LocalDateTime;
import com.example.demo.entity.User;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;

@Entity
public class FinancialProfile {

    @Id
    @GeneratedValue
    private Long id;

    private double monthlyExpenses;
    private double existingLoanEmi;
    private double savingsBalance;

    private LocalDateTime lastUpdatedAt;
@ManyToOne
private User user;

public User getUser() {
    return user;
}

    // ✅ REQUIRED getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public double getExistingLoanEmi() { return existingLoanEmi; }
    public void setExistingLoanEmi(double existingLoanEmi) {
        this.existingLoanEmi = existingLoanEmi;
    }

    public double getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
}

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

    // Constructors
    public FinancialProfile() {}

    public FinancialProfile(User user, double monthlyIncome, double existingDebt, int creditScore) {
        this.user = user;
        this.monthlyIncome = monthlyIncome;
        this.existingDebt = existingDebt;
        this.creditScore = creditScore;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { this.monthlyIncome = monthlyIncome; }

    public double getExistingDebt() { return existingDebt; }
    public void setExistingDebt(double existingDebt) { this.existingDebt = existingDebt; }

    public int getCreditScore() { return creditScore; }
    public void setCreditScore(int creditScore) { this.creditScore = creditScore; }
}

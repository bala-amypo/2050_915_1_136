package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "financial_profiles")
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship: One profile per user. 
    // JsonIgnoreProperties prevents infinite loops by hiding user-internal lists.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    @JsonIgnoreProperties({"password", "loanRequests", "createdAt", "role"})
    private User user;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.01", message = "Monthly income must be greater than 0")
    @Column(nullable = false)
    private Double monthlyIncome;

    @NotNull(message = "Monthly expenses are required")
    @Min(value = 0, message = "Monthly expenses cannot be negative")
    @Column(nullable = false)
    private Double monthlyExpenses;

    private Double existingLoanEmi; // Optional field

    @NotNull(message = "Credit score is required")
    @Min(value = 300, message = "Credit score must be at least 300")
    @Max(value = 900, message = "Credit score cannot exceed 900")
    @Column(nullable = false)
    private Integer creditScore;

    @NotNull(message = "Savings balance is required")
    @Min(value = 0, message = "Savings balance cannot be negative")
    @Column(nullable = false)
    private Double savingsBalance;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    // --- CONSTRUCTORS ---

    // 1. No-args constructor (Required by JPA)
    public FinancialProfile() {
    }

    // 2. Core fields constructor
    public FinancialProfile(User user, Double monthlyIncome, Double monthlyExpenses, 
                            Integer creditScore, Double savingsBalance) {
        this.user = user;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.creditScore = creditScore;
        this.savingsBalance = savingsBalance;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    // --- LIFECYCLE HOOKS ---
    // Automatically updates the timestamp before saving or updating
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedAt = LocalDateTime.now();
    }

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(Double monthlyIncome) { this.monthlyIncome = monthlyIncome; }

    public Double getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(Double monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }

    public Double getExistingLoanEmi() { return existingLoanEmi; }
    public void setExistingLoanEmi(Double existingLoanEmi) { this.existingLoanEmi = existingLoanEmi; }

    public Integer getCreditScore() { return creditScore; }
    public void setCreditScore(Integer creditScore) { this.creditScore = creditScore; }

    public Double getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(Double savingsBalance) { this.savingsBalance = savingsBalance; }

    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
}
package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "loan_request")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double requestedAmount;

    @Column(nullable = false)
    private Integer tenureMonths;

    private String purpose;

    @Column(nullable = false)
    private String status = "PENDING"; 

    @Column(nullable = false, updatable = false)
    private Timestamp appliedAt;

    public LoanRequest() {
    }

    // Parameterized constructor
    public LoanRequest(User user,
                       Double requestedAmount,
                       Integer tenureMonths,
                       String purpose) {
        this.user = user;
        this.requestedAmount = requestedAmount;
        this.tenureMonths = tenureMonths;
        this.purpose = purpose;
        this.status = "PENDING";
    }

    // ===== Validation & Timestamp =====
    @PrePersist
    protected void onCreate() {
        if (requestedAmount == null || requestedAmount <= 0) {
            throw new IllegalArgumentException("Requested amount must be greater than 0");
        }

        if (tenureMonths == null || tenureMonths <= 0) {
            throw new IllegalArgumentException("Tenure months must be greater than 0");
        }

        this.appliedAt = new Timestamp(System.currentTimeMillis());
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAppliedAt() {
        return appliedAt;
    }
}

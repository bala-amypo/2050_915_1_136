package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "loan_requests")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double requestedAmount;  // use this name consistently
    private int tenureMonths;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String status;
    private java.time.LocalDateTime submittedAt;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public java.time.LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(java.time.LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

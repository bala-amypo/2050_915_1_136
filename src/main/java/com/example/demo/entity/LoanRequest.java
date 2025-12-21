package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_amount", nullable = false)
    private double requestedAmount;

    @Column(name = "tenure_months", nullable = false)
    private int tenureMonths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
  

@PrePersist
protected void onCreate() {
    this.submittedAt = LocalDateTime.now();
}

    // 🔹 No-arg constructor (required by JPA)
    public LoanRequest() {
    }

    // 🔹 Optional constructor
    public LoanRequest(double requestedAmount, int tenureMonths, User user, String status) {
        this.requestedAmount = requestedAmount;
        this.tenureMonths = tenureMonths;
        this.user = user;
        this.status = status;
        this.submittedAt = LocalDateTime.now();
    }

    // 🔹 Getters and Setters

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}

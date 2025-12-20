package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;
    private Integer tenureMonths; // Added to fix "cannot find symbol setTenureMonths"

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    private LocalDateTime submittedAt; // Added to fix "cannot find symbol getSubmittedAt"

    public enum Status { PENDING, APPROVED, REJECTED }

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double amount) { this.requestedAmount = amount; }
    
    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    @Override
    public String toString() {
        return status != null ? status.name() : "";
    }
}
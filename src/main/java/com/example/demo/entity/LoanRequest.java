package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;

    public enum Status { PENDING, APPROVED, REJECTED }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double amount) { this.requestedAmount = amount; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return status != null ? status.name() : "";
    }
}
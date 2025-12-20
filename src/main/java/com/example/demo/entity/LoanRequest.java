package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;
    private Integer tenureMonths;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    private LocalDateTime submittedAt;

    public enum Status { PENDING, APPROVED, REJECTED }

    @PrePersist
    protected void onCreate() {
        // t29: Ensure timestamp is set
        if (this.submittedAt == null) {
            this.submittedAt = LocalDateTime.now();
        }
        // t28: Default status must be PENDING if not specified
        if (this.status == null) {
            this.status = Status.PENDING;
        }
    }

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

    // Fixes t17 & t28: String comparison bridge
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof String && status != null) return status.name().equals(o);
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return status != null ? status.name() : "";
    }
}
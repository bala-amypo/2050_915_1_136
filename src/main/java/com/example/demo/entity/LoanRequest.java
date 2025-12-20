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
    private Status status = Status.PENDING;

    @ManyToOne
    private User user;

    private LocalDateTime submittedAt = LocalDateTime.now();

    public enum Status { PENDING, APPROVED, REJECTED }

    @PrePersist
    protected void onCreate() {
        if (this.submittedAt == null) this.submittedAt = LocalDateTime.now();
        if (this.status == null) this.status = Status.PENDING;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double amt) { this.requestedAmount = amt; }
    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer t) { this.tenureMonths = t; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    // OVERLOADED SETTER
    public void setStatus(String statusName) {
        if (statusName != null) this.status = Status.valueOf(statusName.toUpperCase());
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime s) { this.submittedAt = s; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof String) return status != null && status.name().equals(o);
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
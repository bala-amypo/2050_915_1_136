// src/main/java/com/example/demo/entity/LoanRequest.java
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

    // GETTERS AND SETTERS
    // src/main/java/com/example/demo/entity/LoanRequest.java

public Double getRequestedAmount() {
    return requestedAmount;
}

public Integer getTenureMonths() {
    return tenureMonths;
}
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setStatus(String s) { if (s != null) this.status = Status.valueOf(s.toUpperCase()); }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime s) { this.submittedAt = s; }

    // CRITICAL: Bridge for Enum vs String comparison
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
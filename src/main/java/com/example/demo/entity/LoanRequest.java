package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
public class LoanRequest {
@Column(name = "submitted_at")
private LocalDateTime submittedAt;

public LocalDateTime getSubmittedAt() {
    return submittedAt;
}

public void setSubmittedAt(LocalDateTime submittedAt) {
    this.submittedAt = submittedAt;
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_amount", nullable = false)
    private Double requestedAmount;

    @Column(name = "tenure_months", nullable = false)
    private Integer tenureMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "eligibility_result")
    private String eligibilityResult;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // 🔥 REQUIRED ENUM
    public enum Status {
        SUBMITTED,
        APPROVED,
        REJECTED
    }

    public LoanRequest() {}

    @PrePersist
    protected void onSubmit() {
        this.submittedAt = LocalDateTime.now();
        this.status = Status.SUBMITTED;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }

    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getEligibilityResult() { return eligibilityResult; }
    public void setEligibilityResult(String eligibilityResult) { this.eligibilityResult = eligibilityResult; }

  

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public enum Status {
    PENDING,
    SUBMITTED,
    APPROVED,
    REJECTED
}

}

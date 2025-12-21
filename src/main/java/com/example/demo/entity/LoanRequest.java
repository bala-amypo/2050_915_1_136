package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assessment_result", nullable = false)
    private String assessmentResult;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    // getters & setters
}

        // getters & setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Double getRequestedAmount() { return requestedAmount; }
        public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }

        public Integer getTenureMonths() { return tenureMonths; }
        public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getEligibilityResult() { return eligibilityResult; }
        public void setEligibilityResult(String eligibilityResult) { this.eligibilityResult = eligibilityResult; }

        public LocalDateTime getSubmittedAt() { return submittedAt; }
        public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }

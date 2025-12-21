package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_results")
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;

    @Column(nullable = false)
    private Boolean isEligible;

    @Column(nullable = false)
    private Double maxEligibleAmount;

    @Column(nullable = false)
    private Double estimatedEmi;

    @Column(nullable = false)
    private String riskLevel;

    private String rejectionReason;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    @PrePersist
    public void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    public EligibilityResult() {}

    // getters and setters
}


    public EligibilityResult(LoanRequest loanRequest, boolean eligible, String reason) {
        this.loanRequest = loanRequest;
        this.eligible = eligible;
        this.reason = reason;
    }

    // Getters and setters
    public Long getId() { return id; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }

    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}

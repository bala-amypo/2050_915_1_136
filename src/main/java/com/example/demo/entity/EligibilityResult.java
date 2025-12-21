package com.example.demo.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest; // Link to LoanRequest

    private boolean eligible;
    private String reason;

    @Column(name = "calculated_at", nullable = false)
    private LocalDateTime calculatedAt;

    @PrePersist
    protected void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    // Add Getter and Setter for calculatedAt
}
    // Constructors
    public EligibilityResult() {}

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

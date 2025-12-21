package com.example.demo.entity;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(name = "submitted_at", nullable = false)
    private java.time.LocalDateTime submittedAt;

    // getters & setters (same as you already have)
}

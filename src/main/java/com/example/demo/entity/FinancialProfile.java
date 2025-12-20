package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private double income;

    public User getUser() {
        return user;
    }

    public double getIncome() {
        return income;
    }
}

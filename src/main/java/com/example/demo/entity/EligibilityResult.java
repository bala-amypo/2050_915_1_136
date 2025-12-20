package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private boolean eligible;

    public void setUser(User user) {
        this.user = user;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
}

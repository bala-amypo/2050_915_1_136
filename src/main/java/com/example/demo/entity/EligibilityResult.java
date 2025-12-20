package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}

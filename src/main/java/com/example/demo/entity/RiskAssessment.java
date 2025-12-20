package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue
    private Long id;

    private double dtiRatio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getDtiRatio() { return dtiRatio; }
}

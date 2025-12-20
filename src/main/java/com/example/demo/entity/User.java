package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;

    // ✅ FIX FOR created_at DB ERROR
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // One-to-many relationship with LoanRequest
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanRequest> loanRequests;

    // Constructors
    public User() {
    }

    public User(String fullName, String email, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ✅ Automatically set createdAt before insert
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<LoanRequest> getLoanRequests() {
        return loanRequests;
    }

    public void setLoanRequests(List<LoanRequest> loanRequests) {
        this.loanRequests = loanRequests;
    }

    // Enum for roles
    public enum Role {
        CUSTOMER,
        ADMIN
    }
}

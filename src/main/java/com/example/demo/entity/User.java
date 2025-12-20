package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String fullName; // Added to fix test errors

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        CUSTOMER,
        ADMIN
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; } // Added
    public void setFullName(String fullName) { this.fullName = fullName; } // Added

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    /**
     * Helper setter to handle tests that pass Role as a String.
     * This fixes the "incompatible types: String cannot be converted to Role" error.
     */
    public void setRole(String roleName) {
        if (roleName != null) {
            this.role = Role.valueOf(roleName.toUpperCase());
        }
    }
}
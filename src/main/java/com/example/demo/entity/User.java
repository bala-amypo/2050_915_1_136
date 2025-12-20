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

    // Use the Enum here
    @Enumerated(EnumType.STRING)
    private Role role;

    // Define the Enum inside the User class
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
    
    // Update these to use the Role Enum type
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
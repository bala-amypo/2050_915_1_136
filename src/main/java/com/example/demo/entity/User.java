package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    public enum Role { CUSTOMER, ADMIN }

    @PrePersist
    protected void onCreate() {
        // t29: Ensure timestamp is never null
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public void setRole(String roleName) {
        if (roleName != null) {
            this.role = Role.valueOf(roleName.toUpperCase());
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof String) return Objects.equals(this.role.name(), o);
    return false;
}
    @Override
    public String toString() {
        return role != null ? role.name() : "";
    }
}
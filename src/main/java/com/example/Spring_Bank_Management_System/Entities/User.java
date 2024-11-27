package com.example.Spring_Bank_Management_System.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal database ID 

    @Column(unique = true, nullable = false)
    private String userId; // Custom user ID pattern

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String passwordHash; // Remember this hsould alwayss be a hashed password

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void generateUserId() {
        // Generate a unique user ID (e.g., "abu-00001")
        String initials = firstName.substring(0, 1).toLowerCase() + lastName.substring(0, 1).toLowerCase();
        this.userId = initials + "u-" + String.format("%05d", id);
    }

    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    // Hash password before saving
    public void setPassword(String rawPassword) {
        this.passwordHash = new BCryptPasswordEncoder().encode(rawPassword);
    }
}

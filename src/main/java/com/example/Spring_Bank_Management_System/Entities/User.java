package com.example.Spring_Bank_Management_System.Entities;

import jakarta.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    @NotEmpty(message = "The First name field cannot be empty")
    @Size(min = 3, message = "The First name field must be greater than 3 characters")
    private String first_name;

    @Column(nullable = false)
    @NotEmpty(message = "The Last name field cannot be empty")
    @Size(min = 3, message = "The Last name field must be greater than 3 characters")
    private String last_name;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Enter a Valid Email")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    @NotNull(message = "ID number is required to proceed")  
    private String id_num;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be empty")
    private String password; // Remember this hsould alwayss be a hashed password

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated_at = LocalDateTime.now();

    @PrePersist
    public void generateUserId() {
        // Generate a unique user ID (e.g., "abu-00001")
        String initials = first_name.substring(0, 1).toLowerCase() + last_name.substring(0, 1).toLowerCase();
        this.userId = initials + "u-" + String.format("%05d", id);
    }

    @PreUpdate
    public void updateTimestamp() {
        this.updated_at = LocalDateTime.now();
    }

    // Hash password before saving
    public void setPassword(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    private String token;
    private String code;
    private int verified = 0;
}

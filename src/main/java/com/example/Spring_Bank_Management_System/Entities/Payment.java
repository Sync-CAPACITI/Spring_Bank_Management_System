package com.example.Spring_Bank_Management_System.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    
    private int account_id;
    private String beneficiary;
    private String beneficiary_acc_no;
    
    // Change amount to BigDecimal to handle precision better for financial transactions
    private BigDecimal amount;

    private String reference_no;
    private String status;
    private String reason_code;
    
    // Default created_at to current timestamp (can also be handled with @PrePersist)
    private LocalDateTime created_at;
  
    @PrePersist
    public void prePersist() {
        // Set created_at if it's not already set
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
    }
}
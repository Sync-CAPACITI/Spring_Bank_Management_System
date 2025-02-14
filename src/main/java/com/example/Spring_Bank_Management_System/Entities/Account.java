package com.example.Spring_Bank_Management_System.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id") // Explicit column mapping
    private int accountId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance ) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void getAccountType(String accountType ) {
        this.accountType = accountType;
    }



}

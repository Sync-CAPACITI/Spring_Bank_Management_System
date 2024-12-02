package com.example.Spring_Bank_Management_System.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;
    
    @Column(name = "account_id", nullable = false) 
    private int account_id;
    private int user_id;
    private String account_number;
    private String transaction_type; // "DEPOSIT", "WITHDRAWAL", or "TRANSFER"
    private BigDecimal amount;
    private LocalDateTime transaction_date = LocalDateTime.now();
    private String description;



    public void setAccountNumber(String accountNumber) {
        this.account_number = accountNumber;
    }
    
    public void setTransactionType(String transactionType) {
        this.transaction_type = transactionType;
    }
    
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transaction_date = transactionDate;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}

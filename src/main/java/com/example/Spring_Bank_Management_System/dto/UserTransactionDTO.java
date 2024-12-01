package com.example.Spring_Bank_Management_System.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;



public class UserTransactionDTO {
    private Integer transactionId;
    private Integer accountId;
    private String accountNumber;
    private BigDecimal amount;
    private String description;
    private Timestamp transactionDate;
    private String transactionType;
    private Integer destinationAccountId;
    private String destinationAccountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;


    public UserTransactionDTO(Integer transactionId, Integer accountId, String accountNumber, BigDecimal amount, 
                          String description, Timestamp transactionDate, String transactionType, 
                          Integer destinationAccountId, String destinationAccountNumber, 
                          String accountName, String accountType, BigDecimal balance) {
     this.transactionId = transactionId;
     this.accountId = accountId;
     this.accountNumber = accountNumber;
     this.amount = amount;
     this.description = description;
     this.transactionDate = transactionDate;
     this.transactionType = transactionType;
     this.destinationAccountId = destinationAccountId;
     this.destinationAccountNumber = destinationAccountNumber;
     this.accountName = accountName;
     this.accountType = accountType;
     this.balance = balance;
}

    // Getters and setters
}

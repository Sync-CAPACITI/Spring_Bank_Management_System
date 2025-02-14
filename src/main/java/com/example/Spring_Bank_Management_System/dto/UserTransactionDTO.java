package com.example.Spring_Bank_Management_System.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserTransactionDTO {
    private Integer transaction_id;
    private Integer account_id;
    private String account_number;
    private BigDecimal amount;         // Change to BigDecimal
    private String description;
    private Timestamp transaction_date;
    private String transaction_type;
    private String destination_account_number;
    private String account_name;
    private Double balance;

    // Constructor with parameters in the exact order of the query
    public UserTransactionDTO(Integer transactionId, Integer accountId, String accountNumber, 
                            BigDecimal amount, String description, Timestamp transactionDate, 
                            String transactionType, String destinationAccountNumber, 
                            String accountName, Double balance) {
        this.transaction_id = transactionId;
        this.account_id = accountId;
        this.account_number = accountNumber;
        this.amount = amount;           // Ensure correct type
        this.description = description;
        this.transaction_date = transactionDate;
        this.transaction_type = transactionType;
        this.destination_account_number = destinationAccountNumber;
        this.account_name = accountName;
        this.balance = balance;
    }

    // Getters and setters
    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Timestamp transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getDestination_account_number() {
        return destination_account_number;
    }

    public void setDestination_account_number(String destination_account_number) {
        this.destination_account_number = destination_account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

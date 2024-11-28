package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private double currentBalance = 3000.75; // Example starting balance

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest) {
        if (depositRequest.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Amount must be positive.");
        }

        synchronized (this) {
            currentBalance += depositRequest.getAmount();
        }
        return ResponseEntity.ok(String.valueOf(currentBalance));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        double withdrawAmount = withdrawRequest.getAmount();

        if (withdrawAmount <= 0) {
            return ResponseEntity.badRequest().body("Error: Withdrawal amount must be greater than zero.");
        }

        if (withdrawAmount > currentBalance) {
            return ResponseEntity.badRequest().body("Error: Insufficient balance.");
        }

        synchronized (this) {
            currentBalance -= withdrawAmount; // Deduct amount from balance
        }

        return ResponseEntity.ok(String.valueOf(currentBalance));
    }

    // Helper classes for the request payloads
    public static class DepositRequest {
        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    public static class WithdrawRequest {
        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}

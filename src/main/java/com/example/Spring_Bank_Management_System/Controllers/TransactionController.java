package com.example.Spring_Bank_Management_System.Controllers;

import com.example.Spring_Bank_Management_System.Entities.Transact;
import com.example.Spring_Bank_Management_System.repository.TransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactRepository transactRepository;

    private BigDecimal currentBalance = BigDecimal.valueOf(3000.75); // Example starting balance

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest) {
        if (depositRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body("Amount must be positive.");
        }

        synchronized (this) {
            currentBalance = currentBalance.add(depositRequest.getAmount());
        }

        // Create and save a new transaction record
        Transact depositTransaction = new Transact();
        depositTransaction.setAmount(depositRequest.getAmount());
        depositTransaction.setTransaction_type("Deposit");
        depositTransaction.setStatus("Success");
        transactRepository.save(depositTransaction);

        return ResponseEntity.ok("Deposit successful. New balance: " + currentBalance);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        if (withdrawRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body("Error: Withdrawal amount must be greater than zero.");
        }

        if (withdrawRequest.getAmount().compareTo(currentBalance) > 0) {
            return ResponseEntity.badRequest().body("Error: Insufficient balance.");
        }

        synchronized (this) {
            currentBalance = currentBalance.subtract(withdrawRequest.getAmount());
        }

        // Create and save a new transaction record
        Transact withdrawTransaction = new Transact();
        withdrawTransaction.setAmount(withdrawRequest.getAmount());
        withdrawTransaction.setTransaction_type("Withdraw");
        withdrawTransaction.setStatus("Success");
        transactRepository.save(withdrawTransaction);

        return ResponseEntity.ok("Withdrawal successful. New balance: " + currentBalance);
    }

    // Helper classes for request payloads
    public static class DepositRequest {
        private BigDecimal amount;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    public static class WithdrawRequest {
        private BigDecimal amount;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}

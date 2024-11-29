package com.example.Spring_Bank_Management_System.rest_controllers;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.Transaction;
import com.example.Spring_Bank_Management_System.repository.TransactionRepository;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Deposit Endpoint
    @PostMapping("/deposit")
    public String deposit(@RequestParam("account_number") String account_number,
                          @RequestParam("amount") BigDecimal amount,
                          @RequestParam("description") String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }

        try {
            // Fetch account and update balance
            Account account = accountRepository.findByAccountNumber(account_number);
            if (account == null) {
                return "Account not found.";
            }

            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);

            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setAccount_id(account.getAccountId());
            transaction.setAccountNumber(account_number);
            transaction.setTransactionType("DEPOSIT");
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setDescription(description);
            transactionRepository.save(transaction);

            return "Deposit successful. New Balance: " + account.getBalance();
        } catch (Exception e) {
            return "Error occurred during deposit: " + e.getMessage();
        }
    }

    // Withdrawal Endpoint
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("account_number") String account_number,
                           @RequestParam("amount") BigDecimal amount,
                           @RequestParam("description") String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }

        try {
            // Fetch account and validate balance
            Account account = accountRepository.findByAccountNumber(account_number);
            if (account == null) {
                return "Account not found.";
            }
            if (account.getBalance().compareTo(amount) < 0) {
                return "Insufficient balance.";
            }

            // Update account balance
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);

            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setAccount_id(account.getAccountId());
            transaction.setAccountNumber(account_number);
            transaction.setTransactionType("WITHDRAWAL");
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setDescription(description);
            transactionRepository.save(transaction);

            return "Withdrawal successful. New Balance: " + account.getBalance();
        } catch (Exception e) {
            return "Error occurred during withdrawal: " + e.getMessage();
        }
    }

    // Transfer Endpoint
    @PostMapping("/transfer")
    public String transfer(@RequestParam("from_account") String from_account,
                           @RequestParam("to_account") String to_account,
                           @RequestParam("amount") BigDecimal amount,
                           @RequestParam("description") String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }

        try {
            // Fetch accounts
            Account sourceAccount = accountRepository.findByAccountNumber(from_account);
            Account destinationAccount = accountRepository.findByAccountNumber(to_account);

            if (sourceAccount == null || destinationAccount == null) {
                return "One or both accounts not found.";
            }
            if (sourceAccount.getBalance().compareTo(amount) < 0) {
                return "Insufficient balance in source account.";
            }

            // Update account balances
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);

            // Create transaction records
            Transaction debitTransaction = new Transaction();
            debitTransaction.setAccount_id(sourceAccount.getAccountId());
            debitTransaction.setAccountNumber(from_account);
            debitTransaction.setTransactionType("TRANSFER_OUT");
            debitTransaction.setAmount(amount);
            debitTransaction.setTransactionDate(LocalDateTime.now());
            debitTransaction.setDescription(description);
            transactionRepository.save(debitTransaction);

            Transaction creditTransaction = new Transaction();
            creditTransaction.setAccount_id(destinationAccount.getAccountId());
            creditTransaction.setAccountNumber(to_account);
            creditTransaction.setTransactionType("TRANSFER_IN");
            creditTransaction.setAmount(amount);
            creditTransaction.setTransactionDate(LocalDateTime.now());
            creditTransaction.setDescription(description);
            transactionRepository.save(creditTransaction);

            return "Transfer successful. Source Balance: " + sourceAccount.getBalance() +
                   ", Destination Balance: " + destinationAccount.getBalance();
        } catch (Exception e) {
            return "Error occurred during transfer: " + e.getMessage();
        }
    }
}

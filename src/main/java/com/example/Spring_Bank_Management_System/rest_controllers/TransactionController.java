package com.example.Spring_Bank_Management_System.rest_controllers;
import java.util.List;
import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.Transaction;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.TransactionRepository;

import jakarta.servlet.http.HttpSession;

import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
            @RequestParam("description") String description, HttpSession session) {
    
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }
    
        try {
            // Fetch the logged-in user from the session
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "User not logged in.";
            }
    
            // Fetch account and update balance
            Account account = accountRepository.findByAccountNumber(account_number);
            if (account == null) {
                return "Account not found.";
            }
    
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
    
            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setUser(user); // Link the transaction to the logged-in user
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
            @RequestParam("description") String description, HttpSession session) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }

        try {
            // Fetch account and validate balance

            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "User not logged in.";
            }
    
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
            transaction.setAccount(account); // Set the entire Account entity
            transaction.setAccountNumber(account_number);
            transaction.setUser(user);
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
            @RequestParam("description") String description, HttpSession session) {
    
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }
    
        try {
            // Fetch the logged-in user from the session
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "User not logged in.";
            }
    
            // Fetch accounts and perform transfer logic
            Account sourceAccount = accountRepository.findByAccountNumber(from_account);
            Account destinationAccount = accountRepository.findByAccountNumber(to_account);
    
            if (sourceAccount == null || destinationAccount == null) {
                return "One or both accounts not found.";
            }
    
            if (sourceAccount.getBalance().compareTo(amount) < 0) {
                return "Insufficient balance in source account.";
            }
    
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
    
            // Create debit transaction
            Transaction debitTransaction = new Transaction();
            debitTransaction.setAccount(sourceAccount);
            debitTransaction.setUser(user); // Associate with logged-in user
            debitTransaction.setAccountNumber(from_account);
            debitTransaction.setTransactionType("TRANSFER_OUT");
            debitTransaction.setAmount(amount);
            debitTransaction.setTransactionDate(LocalDateTime.now());
            debitTransaction.setDescription(description);
            debitTransaction.setDestinationAccountNumber(to_account);
            transactionRepository.save(debitTransaction);
    
            // Create credit transaction
            Transaction creditTransaction = new Transaction();
            creditTransaction.setAccount(destinationAccount);
            creditTransaction.setUser(user); // Associate with logged-in user
            creditTransaction.setAccountNumber(to_account);
            creditTransaction.setTransactionType("TRANSFER_IN");
            creditTransaction.setAmount(amount);
            creditTransaction.setTransactionDate(LocalDateTime.now());
            creditTransaction.setDescription(description);
            creditTransaction.setDestinationAccountNumber(from_account);
            transactionRepository.save(creditTransaction);
    
            return "Transfer successful. Source Balance: " + sourceAccount.getBalance() +
                    ", Destination Balance: " + destinationAccount.getBalance();
        } catch (Exception e) {
            return "Error occurred during transfer: " + e.getMessage();
        }
    }
    

    @GetMapping("/transactionsList/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable("userId") int userId, HttpSession session) {
        // Get the logged-in user from the session
        User sessionUser = (User) session.getAttribute("user");
    
        // Validate session user
        if (sessionUser == null || sessionUser.getUser_id() != userId) {
            throw new SecurityException("Unauthorized access or user not logged in.");
        }
    
        // Fetch transactions for the user
        return transactionRepository.findByUser(sessionUser);
    }
    


}

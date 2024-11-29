package com.example.Spring_Bank_Management_System.rest_controllers;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.helpers.GenAccountNumber;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create")
    public String createAccount(@RequestParam("user_id") int user_Id,
                                @RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType) {
        if (accountName == null || accountName.trim().isEmpty()) {
            return "Account Name cannot be empty!";
        }
        if (accountType == null || accountType.trim().isEmpty()) {
            return "Account Type cannot be empty!";
        }
        try {
            String bankAccountNumber = GenAccountNumber.generateAccountNumber();
            Account account = new Account();
            account.setUserId(user_Id);
            account.setAccountNumber(bankAccountNumber);
            account.setAccountName(accountName);
            account.setAccountType(accountType);
            account.setBalance(BigDecimal.ZERO); // Explicitly setting default balance
            accountRepository.save(account);
            return "Account Created Successfully with Account Number: " + bankAccountNumber;
        } catch (Exception e) {
            return "Error occurred while creating account: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
}

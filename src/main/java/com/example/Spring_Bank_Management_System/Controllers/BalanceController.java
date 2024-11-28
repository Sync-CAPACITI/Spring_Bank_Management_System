package com.example.Spring_Bank_Management_System.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BalanceController {

    // Placeholder balance (for simplicity, this can later connect to a database or service)
    private double balance = 3000.75;

    @GetMapping("/balance")
    public String getBalance() {
        return String.valueOf(balance); // Returns balance as plain text
    }
}

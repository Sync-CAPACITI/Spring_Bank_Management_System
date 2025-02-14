package com.example.Spring_Bank_Management_System.rest_controllers;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.helpers.GenAccountNumber;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/accounts")
public class RestAccountCreationController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create")
    public String createAccount(
                                @RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {

        if (accountName == null || accountName.trim().isEmpty() || accountType == null || accountType.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "Account name and type cannot be empty!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }

        try {
            String bankAccountNumber = GenAccountNumber.generateAccountNumber();
            LocalDateTime now = LocalDateTime.now();
            User user = (User)session.getAttribute("user");

            if (user == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "User session is invalid. Please log in again.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/login";
            }
            
            
            accountRepository.createBankAccount(user.getUserId(), bankAccountNumber, accountName, accountType, BigDecimal.ZERO, now);

            // Add success message
            redirectAttributes.addFlashAttribute("toastMessage", "Account created successfully!");
            redirectAttributes.addFlashAttribute("toastType", "success");
            return "redirect:/app/home";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("toastMessage", "An error occurred while creating the account. Please try again.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
    }
    

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
}

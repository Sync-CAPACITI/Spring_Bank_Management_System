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


        if (accountName == null || accountName.trim().isEmpty()) {
            return "redirect:/app/home";
        }
        if (accountType == null || accountType.trim().isEmpty()) {

            return "redirect:/app/home";

        }
        try {
            String bankAccountNumber = GenAccountNumber.generateAccountNumber();
            LocalDateTime now = LocalDateTime.now();
            User user = (User)session.getAttribute("user");
            
            
            accountRepository.createBankAccount(user.getUserId(), bankAccountNumber, accountName, accountType, BigDecimal.ZERO, now);

                    // CHECK FOR EMPTY STRINGS:
            if(accountName.isEmpty() || accountType.isEmpty()){
                redirectAttributes.addFlashAttribute("error", "Account Holder Name and Type Cannot be Empty!");
                return "redirect:/app/home";

            }


            redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
            return "redirect:/app/home";


            


        } catch (Exception e) {
            return "redirect:/app/home";
        }
    }
    

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
}

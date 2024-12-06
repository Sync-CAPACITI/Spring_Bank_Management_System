package com.example.Spring_Bank_Management_System.rest_controllers;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.helpers.GenAccountNumber;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/accounts")
public class RestAccountCreationController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create")
    public ModelAndView createAccount(
                                @RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {

        ModelAndView dashboard = new ModelAndView("home");

        if (accountName == null || accountName.trim().isEmpty()) {
            return dashboard;
        }
        if (accountType == null || accountType.trim().isEmpty()) {
            return dashboard;
        }
        try {
            String bankAccountNumber = GenAccountNumber.generateAccountNumber();
            LocalDateTime now = LocalDateTime.now();
            User user = (User)session.getAttribute("user");
            
            
            accountRepository.createBankAccount(user.getUser_id(), bankAccountNumber, accountName, accountType, BigDecimal.ZERO, now);

                    // CHECK FOR EMPTY STRINGS:
            if(accountName.isEmpty() || accountType.isEmpty()){
                redirectAttributes.addFlashAttribute("error", "Account Holder Name and Type Cannot be Empty!");
                return dashboard;
            }


            redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
            return dashboard;

            


        } catch (Exception e) {
            return  dashboard;
        }
    }
    

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
}

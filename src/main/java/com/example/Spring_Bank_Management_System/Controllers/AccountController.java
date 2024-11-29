package com.example.Spring_Bank_Management_System.Controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.helpers.GenAccountNumber;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {
     @Autowired
    private AccountRepository accountRepository;

    

    @PostMapping("/create_account")
    public String createAccount(@RequestParam("account_name")String accountName,
                                @RequestParam("account_type")String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session){

        // CHECK FOR EMPTY STRINGS:
        if(accountName.isEmpty() || accountType.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Account Name and Type Cannot be Empty!");
            return "redirect:/app/dashboard";
        }

        // GET LOGGED IN USER:
        User user = (User)session.getAttribute("user");

        // GET / GENERATE ACCOUNT NUMBER:

        String bankAccountNumber = GenAccountNumber.generateAccountNumber();



        // CREATE ACCOUNT:
        LocalDateTime now = LocalDateTime.now();
        accountRepository.createBankAccount(user.getUser_id(), bankAccountNumber, accountName, accountType , BigDecimal.ZERO, now);

        // Set Success message:
        redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
        return "redirect:/app/dashboard";

    }
}
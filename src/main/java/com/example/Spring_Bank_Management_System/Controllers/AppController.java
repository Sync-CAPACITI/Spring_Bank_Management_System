package com.example.Spring_Bank_Management_System.Controllers;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AccountRepository accountRepository;


    User user;

    @GetMapping("/home")
    public ModelAndView getDashboard(HttpSession session){
        ModelAndView getDashboardPage = new ModelAndView("home");

        // Get the details of the logged i user:
        user = (User)session.getAttribute("user");

        // Debugging: Print the user details
    System.out.println("Logged-in user: " + user);

        // Get The Accounts Of The Logged In User:
        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

        // Debugging: Print the accounts list
        if (getUserAccounts.isEmpty()) {
            System.out.println("No accounts found for user ID: " + user.getUser_id());
        } else {
            System.out.println("Found accounts: " + getUserAccounts.size());
        }

        // Set Objects:
        getDashboardPage.addObject("userAccounts", getUserAccounts);
        
        return getDashboardPage;
    }


}
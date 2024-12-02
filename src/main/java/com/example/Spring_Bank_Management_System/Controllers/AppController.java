package com.example.Spring_Bank_Management_System.Controllers;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.dto.*;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import com.example.Spring_Bank_Management_System.repository.TransactionRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transaction;



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

        // Get The Accounts Of The Logged In User:
        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

        // Get Balance:
        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        // Set Objects:
        getDashboardPage.addObject("userAccounts", getUserAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);
        
        return getDashboardPage;
    }


    @GetMapping("/transfer")
    public ModelAndView getTransfer(HttpSession session){      
        ModelAndView getIndexPage = new ModelAndView("transfer");
        user = (User) session.getAttribute("user");  // Fetch user from session before using it
        

        // Get The Accounts Of The Logged In User:

    List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

      // Ensure the accounts list is not empty
    if (getUserAccounts != null && !getUserAccounts.isEmpty()) {
        getIndexPage.addObject("userAccounts", getUserAccounts);
    } else {
        getIndexPage.addObject("userAccounts", new ArrayList<>()); // In case the list is empty
    }

    return getIndexPage;
    }

    

    
    




}
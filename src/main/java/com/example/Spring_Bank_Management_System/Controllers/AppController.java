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
import com.example.Spring_Bank_Management_System.Entities.Transaction;
import com.example.Spring_Bank_Management_System.Entities.User;

import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import com.example.Spring_Bank_Management_System.repository.TransactionRepository;

import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

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

    

    
    

    @GetMapping("/transactionsList")
    public ModelAndView getTransactions(HttpSession session){
        ModelAndView getIndexPage = new ModelAndView("transactionsList");
        user = (User) session.getAttribute("user"); 

        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());
        List<Transaction> transactions = transactionRepository.findByUser(user);
        // Ensure the accounts list is not empty
        if (getUserAccounts != null && !getUserAccounts.isEmpty()) {
            getIndexPage.addObject("userAccounts", getUserAccounts);
        } else {
            getIndexPage.addObject("userAccounts", new ArrayList<>()); // In case the list is empty
        }
        if (transactions != null && !transactions.isEmpty()) {
            getIndexPage.addObject("transactions", transactions);
        } else {
            getIndexPage.addObject("transactions", new ArrayList<>()); // In case the list is empty
        }


        System.out.println("In The transactions List Page Controller");
        return getIndexPage;
    }


}
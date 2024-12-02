package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.stereotype.Controller;




@Controller
public class DashboardController {


    @GetMapping("/open_account")
    public ModelAndView getAccount(){
        ModelAndView getIndexPage = new ModelAndView("open_account");
        getIndexPage.addObject("PageTitle", "Open_Account");
        System.out.println("In The open_account Controller");
        return getIndexPage;
    }
    @GetMapping("/deposit")
    public ModelAndView getDeposit(){
        ModelAndView getIndexPage = new ModelAndView("deposit");
        getIndexPage.addObject("PageTitle", "Deposit_Cash");
        System.out.println("In The deposit Page Controller");
        return getIndexPage;
    }
    @GetMapping("/withdraw")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("withdraw");
        getIndexPage.addObject("PageTitle", "Withdraw_Cash");
        System.out.println("In The withdraw Page Controller");
        return getIndexPage;
    }
    
    @GetMapping("/transactionsList")
    public ModelAndView getTransactions(){
        ModelAndView getIndexPage = new ModelAndView("transactionsList");
        getIndexPage.addObject("PageTitle", "Withdraw_Cash");
        System.out.println("In The transactions List Page Controller");
        return getIndexPage;
    }
    


}

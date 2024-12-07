package com.example.Spring_Bank_Management_System.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Spring_Bank_Management_System.Entities.User;



import org.springframework.stereotype.Controller;





@Controller
public class DashboardController {
    User user;



  
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
    @GetMapping("/transfer")
    public ModelAndView getTransfer(){
        ModelAndView getIndexPage = new ModelAndView("transfer");
        
        getIndexPage.addObject("PageTitle", "Transfer Cash");
        System.out.println("In The Landing Page Controller");
        return getIndexPage;
    }

}

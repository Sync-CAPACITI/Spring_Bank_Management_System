package com.example.Spring_Bank_Management_System.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class transferController {
     @GetMapping("/transfer")
    public ModelAndView getTransfer(){
        ModelAndView getTrasnferPage = new ModelAndView("transfer");
        getTrasnferPage.addObject("PageTitle", "Transfer");
        System.out.println("In the Transfer Page Controller");
        return getTrasnferPage;
    }

    @GetMapping("/error")
    public ModelAndView getError(){
        ModelAndView getErrorPage = new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Errors");
        System.out.println("In Error Page Controller");
        return getErrorPage;
    }
}

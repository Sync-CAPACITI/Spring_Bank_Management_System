package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
     @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("landing");
        getIndexPage.addObject("PageTitle", "Home");
        System.out.println("In The Landing Page Controller");
        return getIndexPage;
    }


    @GetMapping("/error")
    public ModelAndView getError(){
        ModelAndView getErrorPage = new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Errors");
        System.out.println("In Error Page Controller");
        return getErrorPage;
    }
}

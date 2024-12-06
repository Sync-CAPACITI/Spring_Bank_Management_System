package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.Spring_Bank_Management_System.repository.UserRepository;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

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
        System.out.println("This when we get an error");
        return getErrorPage;
    }

    @GetMapping("/verify")
    public ModelAndView getVerify(@RequestParam("token")String token, @RequestParam("code") String code){
        // Set View:
        ModelAndView getVerifyPage;

        // Get Token In Database:
        String dbToken = userRepository.checkToken(token);

        // // Check If Token Is Valid:
        if(dbToken == null){
            getVerifyPage  = new ModelAndView("error");
            getVerifyPage.addObject("error", "This Session Has Expired");
            return  getVerifyPage;
        }
        // // End Of Check If Token Is Valid.

        // Update and Verify Account:
        userRepository.verifyAccount(token, code);

        getVerifyPage = new ModelAndView("login");

        getVerifyPage.addObject("success", "Account Verified Successfully, Please proceed to Log In!");

        System.out.println("In Verify Account Controller");
        return getVerifyPage;
    }
}

package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return getErrorPage;
    }

    @GetMapping("/verify")
    public String getVerify(@RequestParam("token")String token, @RequestParam("code") String code, RedirectAttributes redirectAttributes){

        // Get Token In Database:
        String dbToken = userRepository.verifyToken(token);

        // Check If Token Is Valid:
        if(dbToken == null){
            redirectAttributes.addFlashAttribute("toastMessage", "This session has expired.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/error";
        }
        // End Of Check If Token Is Valid.

        // Update and Verify Account:
        userRepository.verifyAccount(token, code);

        redirectAttributes.addFlashAttribute("toastMessage", "Account verified successfully! Please proceed to log in.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/login";
    }
}

package com.example.Spring_Bank_Management_System.controler_advisor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Spring_Bank_Management_System.Entities.User;


@ControllerAdvice
public class AdvisorController {

    @ModelAttribute("registerUser")
    public User getUserDefaults(){
        return new User();
    }
}

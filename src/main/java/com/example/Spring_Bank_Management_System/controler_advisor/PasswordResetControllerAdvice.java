package com.example.Spring_Bank_Management_System.controler_advisor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Spring_Bank_Management_System.helpers.PasswordResetForm;

@ControllerAdvice
public class PasswordResetControllerAdvice {

    @ModelAttribute("passwordResetForm")
    public PasswordResetForm getPasswordResetForm() {
        return new PasswordResetForm(); 
    }
}

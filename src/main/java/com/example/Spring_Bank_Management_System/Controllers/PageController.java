package com.example.Spring_Bank_Management_System.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/balance")
    public String showBalancePage() {
        return "balance"; // Maps to balance.jsp
    }
}
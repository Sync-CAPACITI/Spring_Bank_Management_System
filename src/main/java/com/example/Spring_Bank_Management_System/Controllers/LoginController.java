package com.example.Spring_Bank_Management_System.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.helpers.Token;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import com.example.Spring_Bank_Management_System.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    public ModelAndView getLogin(){
        System.out.println("In Login Page Controller");
        ModelAndView getLoginPage = new ModelAndView("login");
        // Set Token String:
        String token = Token.generateToken();
        // Send Token to View:
        getLoginPage.addObject("token", token);
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;
    }

   @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("_token") String token,
                        Model model,
                        HttpSession session) {

        // Validate Input Fields:
        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Username or Password Cannot be Empty");
            return "login";
        }

        // Check if email exists:
        String getEmailInDatabase = userRepository.getUserEmail(email);
        if (getEmailInDatabase == null) {
            model.addAttribute("error", "Incorrect Username or Password");
            return "login";
        }

        // Validate password:
        String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);
        if (!BCrypt.checkpw(password, getPasswordInDatabase)) {
            model.addAttribute("error", "Incorrect Username or Password");
            return "login";
        }

        // Check if account is verified:
        int verified = userRepository.isVerified(getEmailInDatabase);
        if (verified != 1) {
            String msg = "This Account is not yet Verified, please check email and verify account";
            model.addAttribute("error", msg);
            return "login";
        }

        // Proceed to log the user in:
        User user = userRepository.getUserDetails(getEmailInDatabase);

        // Fetch the user's accounts:
        List<Account> userAccounts = accountRepository.findByUserId(user.getUser_id());

        // Set session attributes:
        session.setAttribute("user", user);
        session.setAttribute("token", token);
        session.setAttribute("authenticated", true);

        // If user has accounts, add account details to the session:
        if (!userAccounts.isEmpty()) {
            session.setAttribute("userAccounts", userAccounts);
            session.setAttribute("accountType", userAccounts.get(0).getAccountType()); // Example: get the first account type
        } else {
            session.setAttribute("userAccounts", null);
            session.setAttribute("accountType", null);
        }

        return "redirect:/app/home";
    }
    // End Of Authentication Method.

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully");
        return "redirect:/login";
    }
}

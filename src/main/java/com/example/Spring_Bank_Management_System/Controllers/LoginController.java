package com.example.Spring_Bank_Management_System.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
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
    public UserRepository userRepository;

    @Autowired
    public AccountRepository accountRepository;

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
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {

        // Validate Input Fields:
        if (email.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "Username or Password Cannot be Empty");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/login";
        }

        // Check if email exists:
        String getEmailInDatabase = userRepository.getUserEmail(email);
        if (getEmailInDatabase == null) {
            redirectAttributes.addFlashAttribute("toastMessage", "Incorrect Username or Password");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/login";
        }

        // Validate password:
        String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);
        if (!BCrypt.checkpw(password, getPasswordInDatabase)) {
            redirectAttributes.addFlashAttribute("toastMessage", "Incorrect Username or Password");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/login";
        }

        // Check if account is verified:
        int verified = userRepository.isVerified(getEmailInDatabase);
        if (verified != 1) {
            String msg = "This Account is not yet Verified, please check email and verify account";
            redirectAttributes.addFlashAttribute("toastMessage", msg);
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/login";
        }

        // Proceed to log the user in:
        User user = userRepository.getUserDetails(getEmailInDatabase);
        
        if (!(user.getUserId() <= 0) ){
            // Fetch the user's accounts using the userId
            List<Account> userAccounts = accountRepository.findByUserId(user.getUserId());
        
            // Set session attributes
            session.setAttribute("user", user);
            session.setAttribute("token", token);
            session.setAttribute("authenticated", true);
        
            // If user has accounts, add account details to the session
            if (!userAccounts.isEmpty()) {
                session.setAttribute("userAccounts", userAccounts);
                session.setAttribute("accountType", userAccounts.get(0).getAccountType());
            } else {
                session.setAttribute("userAccounts", null);
                session.setAttribute("accountType", null);
            }
        } else {
            // Handle case where userId is invalid or null
            session.setAttribute("userAccounts", null);
            session.setAttribute("accountType", null);
        }
        
        redirectAttributes.addFlashAttribute("toastMessage", "Login Successful");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/app/home";
    }
    // End Of Authentication Method.

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        // Add a success toast message
        redirectAttributes.addFlashAttribute("toastMessage", "Logged out successfully");
        redirectAttributes.addFlashAttribute("toastType", "success");

        // Redirect to the login page
        return "redirect:/login";
    }
}

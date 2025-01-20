package com.example.Spring_Bank_Management_System.Controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.helpers.HTML;
import com.example.Spring_Bank_Management_System.helpers.Token;
import com.example.Spring_Bank_Management_System.mailMessenger.MailMessenger;
import com.example.Spring_Bank_Management_System.repository.UserRepository;

import jakarta.mail.MessagingException;

@Controller
public class PasswordResetController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/password_reset_request")
    public ModelAndView getPasswordResetRequest() {
        ModelAndView modelAndView = new ModelAndView("password_reset_request");
        modelAndView.addObject("PageTitle", "Password Reset Request");
        return modelAndView;
    }

    @PostMapping("/password_reset_request")
    public String handlePasswordResetRequest(@RequestParam("email") String email, 
                                             RedirectAttributes redirectAttributes) throws MessagingException {
        // Check if the email exists in the database
        String userEmail = userRepository.getUserEmail(email);
        if (userEmail == null) {
            redirectAttributes.addFlashAttribute("error", "Email not found.");
            return "redirect:/password_reset_request";
        }

        // Generate token for password reset
        String token = Token.generateToken();
        int code = new Random().nextInt(999999); // Generate a random code

        // Get the reset email body content with the token and code
        String emailBody = HTML.passwordResetEmailTemplate(token, code);

        // Update the user's token and code in the database (optional)
        userRepository.updateResetToken(email, token);

        // Send the email with the reset link
        MailMessenger.htmlEmailMessenger("noreply@example.com", email, "Password Reset Request", emailBody);

        // Success message
        redirectAttributes.addFlashAttribute("success", "A password reset link has been sent to your email.");
        return "redirect:/login";
    }

    @GetMapping("/password_reset")
    public ModelAndView getPasswordResetPage(@RequestParam("token") String token, Model model) {
        // Check if the token is valid
        String storedToken = userRepository.checkToken(token);
        if (storedToken == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return new ModelAndView("password_reset");
        }

        // Token is valid, show password reset form
        model.addAttribute("token", token);
        return new ModelAndView("password_reset");
    }

    @PostMapping("/password_reset")
    public String handlePasswordReset(@RequestParam("token") String token,
                                      @RequestParam("newPassword") String newPassword,
                                      @RequestParam("confirmPassword") String confirmPassword,
                                      RedirectAttributes redirectAttributes) {
        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/password_reset?token=" + token;
        }

        // Check if the token is valid
        String storedToken = userRepository.checkToken(token);
        if (storedToken == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid or expired token.");
            return "redirect:/password_reset?token=" + token;
        }

        // Hash the new password
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        // Update the user's password
        userRepository.updatePasswordWithToken(token, hashedPassword);

        // Redirect to login page with success message
        redirectAttributes.addFlashAttribute("success", "Your password has been successfully reset.");
        return "redirect:/login";
    }
}




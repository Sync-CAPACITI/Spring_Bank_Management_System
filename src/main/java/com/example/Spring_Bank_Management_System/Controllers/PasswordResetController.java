package com.example.Spring_Bank_Management_System.Controllers;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.helpers.HTML;
import com.example.Spring_Bank_Management_System.helpers.PasswordResetForm;
import com.example.Spring_Bank_Management_System.helpers.Token;
import com.example.Spring_Bank_Management_System.mailMessenger.MailMessenger;
import com.example.Spring_Bank_Management_System.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

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
            redirectAttributes.addFlashAttribute("toastMessage", "Email not found. User does not exist.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/password_reset_request";
        }

        // Generate token for password reset
        String token = Token.generateToken();
        int code = new Random().nextInt(999999); // Generate a random code

        // Set expiration time (15 minutes from now)
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

        // Update the user's token and expiration time in the database 
        userRepository.updateResetToken(email, expirationTime, token);

        // Get the reset email body content with the token and code
        String emailBody = HTML.passwordResetEmailTemplate(token, code);

        // Send the email with the reset link
        MailMessenger.htmlEmailMessenger("noreply9823@gmail.com", email, "Password Reset Request", emailBody);

        // Success message
        redirectAttributes.addFlashAttribute("toastMessage", "A password reset link has been sent to your email.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/login";
    }

    @GetMapping("/password_reset")
    public String getPasswordResetPage(@RequestParam("token") String token, Model model,RedirectAttributes redirectAttributes) {
        // Validate the token
        String storedToken = userRepository.checkToken(token, LocalDateTime.now());
        if (storedToken == null) {
            redirectAttributes.addFlashAttribute("toastMessage", "Invalid or expired token.");
        redirectAttributes.addFlashAttribute("toastType", "error");
        return "redirect:/password_reset_request";
        }

        model.addAttribute("token", token);
        model.addAttribute("passwordResetForm", new PasswordResetForm());
        return "redirect:/password_reset";
    }

    

    @PostMapping("/password_reset")
    public String handlePasswordReset(@Valid @ModelAttribute("passwordResetForm") PasswordResetForm form,
                                    @RequestParam("token") String token,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        // Check if form validation has errors
        if (result.hasErrors()) {
            return "redirect:/password_reset"; 
        }

        // Validate password and confirmation match
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("toastMessage", "Passwords do not match.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/password_reset?token=" + token;
        }

        // Retrieve the user by token and update their password
        User user = userRepository.findUserByToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute("toastMessage", "Invalid or expired token.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/password_reset?token=" + token;
        }

        // Hash the new password and update in the database
        String hashedPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());
        // Attempt to update the password in the repository
        int rowsUpdated = userRepository.updatePasswordWithToken(token, hashedPassword, LocalDateTime.now());

        if (rowsUpdated == 0) {
            // No rows updated, meaning token is invalid or expired
            redirectAttributes.addFlashAttribute("toastMessage", "Invalid or expired token.");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/password_reset?token=" + token;
        }

        // Success, password updated
        redirectAttributes.addFlashAttribute("toastMessage", "Your password has been successfully updated.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/login";
    }


}




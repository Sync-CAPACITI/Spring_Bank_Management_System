package com.example.Spring_Bank_Management_System.Controllers;



import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class LoginControllerTest {


    private LoginController controller; // SUT
    private UserRepository userRepo; // Mock
    private HttpSession session;
    private RedirectAttributes model;
//    private User user;

    //Before vs BeforeEach --> 1. for one method & 2. Should run before each test.
    @BeforeEach
    public void setUp()
            throws Exception {
        userRepo = mock(UserRepository.class);
        session = mock(HttpSession.class);
        model = mock(RedirectAttributes.class);
        //copy code:

        //initialise controller
        controller = new LoginController();  // defined but no values assigned
        controller.userRepository = userRepo; //de

    }

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        String username = "Zitha@gmail.com";
        String password = "zitha123";
        String token = "fd696a2e-bc52-40a9-b5b2-076e55f21b3a";
        User user = new User();
        user.setUserId(1);

        // Create a salt and hashed password (real hashing)
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        // Act
        when(userRepo.getUserEmail(username)).thenReturn(username);  // Mock email check
        when(userRepo.getUserPassword(username)).thenReturn(hashedPassword);  // Mock password check
        when(userRepo.getUserDetails(username)).thenReturn(user);  // Mock user details
        when(userRepo.isVerified(username)).thenReturn(1);  // Mock user verification

        // Run the login method on the controller
        String result = controller.login(username, password, token, model, session);

        // Assert
        assertEquals("redirect:/app/home", result);
    }



}
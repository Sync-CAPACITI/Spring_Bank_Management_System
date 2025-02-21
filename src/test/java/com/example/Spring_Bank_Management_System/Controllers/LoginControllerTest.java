package com.example.Spring_Bank_Management_System.Controllers;



import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import com.example.Spring_Bank_Management_System.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginController controller; // SUT (System Under Test)
    private UserRepository userRepo; // Mocked dependency
    private AccountRepository accountRepository; // Mocked AccountRepository
    private HttpSession session;
    private RedirectAttributes model;

    @BeforeEach
    public void setUp() {
        // Mocking repositories
        userRepo = mock(UserRepository.class);
        accountRepository = mock(AccountRepository.class); // Mock accountRepository

        // Mocking other dependencies
        session = mock(HttpSession.class);
        model = mock(RedirectAttributes.class);

        // Initialize controller and inject mocked repositories
        controller = new LoginController();
        controller.userRepository = userRepo;
        controller.accountRepository = accountRepository; // Inject accountRepository
    }

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        String username = "Zitha@gmail.com";
        String password = "zitha123";
        String token = "fd696a2e-bc52-40a9-b5b2-076e55f21b3a";
        User user = new User();
        user.setUserId(1);

        // Use real BCrypt methods for password hashing
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        // Mock UserRepository methods
        when(userRepo.getUserEmail(username)).thenReturn(username);
        when(userRepo.getUserPassword(username)).thenReturn(hashedPassword);
        when(userRepo.getUserDetails(username)).thenReturn(user);
        when(userRepo.isVerified(username)).thenReturn(1);

        // Mock AccountRepository methods (returning a list of Account)
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());  // Add an Account object to the list
        when(accountRepository.findByUserId(user.getUserId())).thenReturn(accounts); // Return the list // Mock account

        // Act: Call the login method
        String result = controller.login(username, password, token, model, session);

        // Assert: Check the redirect result
        assertEquals("redirect:/app/home", result);
    }
}

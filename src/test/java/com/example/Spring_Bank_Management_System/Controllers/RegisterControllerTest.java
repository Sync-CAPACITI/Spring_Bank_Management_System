package com.example.Spring_Bank_Management_System.Controllers;

import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.UserRepository;
import com.example.Spring_Bank_Management_System.mailMessenger.MailMessenger;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    private RegisterController controller; // SUT
    private UserRepository userRepository; // Mocked dependency
    private BindingResult bindingResult; // Mocked binding
    private RedirectAttributes redirectAttributes; // Mocked dependency

    @BeforeEach
    public void setUp() {
        // Manually creating mocks using Mockito.mock() instead of @Mock
        userRepository = mock(UserRepository.class);
        bindingResult = mock(BindingResult.class);
        redirectAttributes = mock(RedirectAttributes.class);
        

        // Initializing the controller and injecting the mocked dependencies
        controller = new RegisterController();
        controller.userRepository = userRepository;
    
    }

    @Test
    void testRegister_Successful() throws MessagingException {
        // Arrange
        User user = new User();
        user.setFirstName("Kamohelo");
        user.setLastName("Bore");
        user.setEmail("kamohelo.bore@gmail.com");
        user.setIdNum("1234567890123");
        user.setPassword("Password123!");

        String confirmPassword = "Password123!";
        
      // Mocking repository behavior
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(null); // User does not exist

        // Mock static method MailMessenger.htmlEmailMessenger
        try (MockedStatic<MailMessenger> mockedMailMessenger = mockStatic(MailMessenger.class)) {
            // Act
            String result = controller.register(user, bindingResult, confirmPassword, redirectAttributes);

            // Assert
            assertEquals("redirect:/login", result);
            verify(redirectAttributes).addFlashAttribute("toastMessage", "Account Registered Successfully! Please Check your Email and Verify your Account.");
            verify(redirectAttributes).addFlashAttribute("toastType", "success");

            // Verify that the static method was called
            mockedMailMessenger.verify(() -> MailMessenger.htmlEmailMessenger(eq("noreply9823@gmail.com"), eq(user.getEmail()), anyString(), anyString()));
        }
    }

    @Test
    void testRegister_PasswordsDoNotMatch() throws MessagingException {
        // Arrange
        User user = new User();
        user.setEmail("kamohelo.bore@gmail.com");
        user.setPassword("Password123!");
        String confirmPassword = "DifferentPassword123!";

        // Act
        String result = controller.register(user, bindingResult, confirmPassword, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
        verify(redirectAttributes).addFlashAttribute("toastMessage", "Passwords do not match!");
        verify(redirectAttributes).addFlashAttribute("toastType", "error");
    }

    @Test
    void testRegister_InvalidSouthAfricanId() throws MessagingException {
        // Arrange
        User user = new User();
        user.setEmail("Kamhelo.bore@gmail.com");
        user.setIdNum("12345"); // Invalid ID
        user.setPassword("Password123!");
        String confirmPassword = "Password123!";

        // Act
        String result = controller.register(user, bindingResult, confirmPassword, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
        verify(redirectAttributes).addFlashAttribute("toastMessage", "Invalid South African ID number!");
        verify(redirectAttributes).addFlashAttribute("toastType", "error");
    }

    @Test
    void testRegister_UserAlreadyExists() throws MessagingException {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("Password123!");
        String confirmPassword = "Password123!";

        // Mocking repository behavior
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(new User()); // User exists

        // Act
        String result = controller.register(user, bindingResult, confirmPassword, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
        verify(redirectAttributes).addFlashAttribute("toastMessage", "Email address already exists. User cannot be registered.");
        verify(redirectAttributes).addFlashAttribute("toastType", "error");
    }

    @Test
    void testRegister_InvalidPasswordFormat() throws MessagingException {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password"); // Invalid password
        String confirmPassword = "password";

        // Act
        String result = controller.register(user, bindingResult, confirmPassword, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
        verify(redirectAttributes).addFlashAttribute("toastMessage", "Password Must have:UpperCase,Special Character,Number and be 8 Characters long");
        verify(redirectAttributes).addFlashAttribute("toastType", "error");
    }
}

package com.example.Spring_Bank_Management_System.rest_controllers;
import java.util.List;
import com.example.Spring_Bank_Management_System.Entities.Account;
import com.example.Spring_Bank_Management_System.Entities.Transaction;
import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.TransactionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private TransactionRepository transactionRepository;


    User user;
    double currentBalance;
    double newBalance;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @PostMapping("/deposit")
    public String deposit(@RequestParam("amount")BigDecimal amount,
                          @RequestParam("description")String description,
                          @RequestParam("account_id")String accountId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes){

        //CHECK FOR EMPTY STRINGS:
        if(accountId == null || accountId.trim().isEmpty()){
            redirectAttributes.addFlashAttribute("toastMessage", "Account ID cannot be empty!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            redirectAttributes.addFlashAttribute("toastMessage", "Deposit amount must be greater than zero!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }

        try {
            // Fetch the logged-in user from the session
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "User session is invalid. Please log in again.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/login";
            }
      
            // Fetch account and update balance
            Account account = accountRepository.findAccountById(accountId);
            if (account == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "Account not found. Please select a valid account.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/app/home";
            }
            String account_number = accountRepository.findAccountNumber(accountId);
      
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
    
            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setUser(user); // Link the transaction to the logged-in user
            transaction.setAccountNumber(account_number);
            transaction.setTransactionType("DEPOSIT");
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setDescription(description);
            transaction.setDestinationAccountNumber(account_number);
            transactionRepository.save(transaction);
            String success_message = "Amount Deposited Successfully, New Balance:" + account.getBalance();
            redirectAttributes.addFlashAttribute("toastMessage", success_message);
            redirectAttributes.addFlashAttribute("toastType", "success");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("toastMessage", "Error occurred during deposit: " + e.getMessage());
            redirectAttributes.addFlashAttribute("toastType", "error");
        }
        return "redirect:/app/home";
    }

    // Withdrawal Endpoint
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount")BigDecimal amount,
                           @RequestParam("account_id")String accountId,
                           @RequestParam("description")String description,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){

        if (accountId == null || accountId.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "Account ID cannot be empty!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
       

        if (amount == null ||amount.compareTo(BigDecimal.ZERO) <= 0) {
            redirectAttributes.addFlashAttribute("toastMessage", "Withdrawal amount must be greater than zero!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }

        try {
            // Fetch account and validate balance
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "User session is invalid. Please log in again.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/login";
            }
            Account account = accountRepository.findAccountById(accountId);

            if (account == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "Account not found. Please select a valid account.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/app/home";
            }

            if (account.getBalance().compareTo(amount) < 0) {
                redirectAttributes.addFlashAttribute("toastMessage", "Insufficient funds for this withdrawal!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
            }

            // Update account balance
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            String account_number = accountRepository.findAccountNumber(accountId);
            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setAccount(account); // Set the entire Account entity
            transaction.setAccountNumber(account_number);
            transaction.setUser(user);
            transaction.setTransactionType("WITHDRAWAL");
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setDescription(description);
            transactionRepository.save(transaction);

            String success =  "Withdrawal successful. New Balance: " + account.getBalance();
            redirectAttributes.addFlashAttribute("toastMessage", success);
            redirectAttributes.addFlashAttribute("toastType", "success");

        } catch (Exception e) {
            String error = "Error occurred during withdrawal: " + e.getMessage();
            redirectAttributes.addFlashAttribute("toastMessage", error);
            redirectAttributes.addFlashAttribute("toastType", "error");
            
        }
        return "redirect:/app/home";
    }
    // End Of Withdrawal Method.

    // Transfer Endpoint
    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String from_account,
                            @RequestParam("transfer_to") String to_account,
                            @RequestParam("transfer_amount")BigDecimal amount,
                           @RequestParam("description") String description,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (from_account == null || from_account.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "Source account cannot be empty!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
        if (to_account == null || to_account.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "Destination account cannot be empty!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            redirectAttributes.addFlashAttribute("toastMessage", "Transfer amount must be greater than zero!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
        if (from_account.equals(to_account)) {
            redirectAttributes.addFlashAttribute("toastMessage", "Source and destination accounts cannot be the same!");
            redirectAttributes.addFlashAttribute("toastType", "error");
            return "redirect:/app/home";
        }
    
        try {
            // Fetch the logged-in user from the session
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "User session is invalid. Please log in again.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/login";
            }
    
            // Fetch accounts and perform transfer logic
            Account sourceAccount = accountRepository.findAccountById(from_account);
            Account destinationAccount = accountRepository.findAccountById(to_account);
    
            if (sourceAccount == null || destinationAccount == null) {

                redirectAttributes.addFlashAttribute("toastMessage", "One or both accounts not found. Please verify the account IDs.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/app/home";
            }
    
            if (sourceAccount.getBalance().compareTo(amount) < 0) {
              
                redirectAttributes.addFlashAttribute("toastMessage", "Insufficient funds in the source account!");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/app/home";
            }
    
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);

            String sourceAccountNumber = accountRepository.findAccountNumber(from_account);
            String destinationAccountNumber = accountRepository.findAccountNumber(to_account);

            if (sourceAccountNumber == null || destinationAccountNumber == null) {
                redirectAttributes.addFlashAttribute("toastMessage", "One or both accounts not found. Please verify the account IDs.");
                redirectAttributes.addFlashAttribute("toastType", "error");
                return "redirect:/app/home";
            }
             
            // Create debit transaction
            Transaction debitTransaction = new Transaction();
            debitTransaction.setAccount(sourceAccount);
            debitTransaction.setUser(user); // Associate with logged-in user
            debitTransaction.setAccountNumber(sourceAccountNumber);
            debitTransaction.setTransactionType("TRANSFER_OUT");
            debitTransaction.setAmount(amount);
            debitTransaction.setTransactionDate(LocalDateTime.now());
            debitTransaction.setDescription(description);
            debitTransaction.setDestinationAccountNumber(destinationAccountNumber);
            transactionRepository.save(debitTransaction);
    
            // Create credit transaction
            Transaction creditTransaction = new Transaction();
            creditTransaction.setAccount(destinationAccount);
            creditTransaction.setUser(user); // Associate with logged-in user
            creditTransaction.setAccountNumber(sourceAccountNumber);
            creditTransaction.setTransactionType("TRANSFER_IN");
            creditTransaction.setAmount(amount);
            creditTransaction.setTransactionDate(LocalDateTime.now());
            creditTransaction.setDescription(description);
            creditTransaction.setDestinationAccountNumber(destinationAccountNumber);
            transactionRepository.save(creditTransaction);
    
            String success = "Transfer successful. Source Balance: " + sourceAccount.getBalance() +
                    ", Destination Balance: " + destinationAccount.getBalance();
                    redirectAttributes.addFlashAttribute("toastMessage", success);
                    redirectAttributes.addFlashAttribute("toastType", "success");
        } catch (Exception e) {
            String error = "Error occurred during transfer: " + e.getMessage();
            redirectAttributes.addFlashAttribute("toastMessage", error);
            redirectAttributes.addFlashAttribute("toastType", "error");
        }
        return "redirect:/app/home";
    }

    @GetMapping("/transactionsList/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable("userId") int userId, HttpSession session) {
        
        ModelAndView getIndexPage = new ModelAndView("transactionsList");
        // Get the logged-in user from the session
        User sessionUser = (User) session.getAttribute("user");
        List<Transaction> transactions = transactionRepository.findByUser(sessionUser);

        // Ensure the accounts list is not empty
        if (transactions != null && !transactions.isEmpty()) {
            getIndexPage.addObject("transactions", transactions);
        } else {
            getIndexPage.addObject("transactions", new ArrayList<>()); // In case the list is empty
        }

        // Validate session user
        if (sessionUser == null || sessionUser.getUserId() != userId) {
            throw new SecurityException("Unauthorized access or user not logged in.");
        }
    
        // Fetch transactions for the user
        return transactionRepository.findByUser(sessionUser);
    }
    
}

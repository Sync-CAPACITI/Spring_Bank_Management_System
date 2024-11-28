package com.example.Spring_Bank_Management_System.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.repository.AccountRepository;
import com.example.Spring_Bank_Management_System.repository.TransactRepository;


import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

@Controller
public class TransferController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactRepository transactRepository;

    @GetMapping("/transfer")
    public ModelAndView getTransfer(){
        ModelAndView getTrasnferPage = new ModelAndView("transfer");
        getTrasnferPage.addObject("PageTitle", "Transfer");
        System.out.println("In the Transfer Page Controller");
        return getTrasnferPage;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount")String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){
        // Init Error Message Value:
        String errorMessage;
        LocalDateTime currentDateTime = LocalDateTime.now();

        //  CHECK FOR EMPTY FIELDS:
        if(transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()){
             errorMessage = "The account transferring from and to along with the amount cannot be empty!";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        try{
            //  CONVERT VARIABLES:
            int transferFromId = Integer.parseInt(transfer_from);
            int transferToId = Integer.parseInt(transfer_to);
            double transferAmount = Double.parseDouble(transfer_amount);

            //  CHECK IF TRANSFERRING INTO THE SAME ACCOUNT:
            if(transferFromId == transferToId){
                errorMessage = "Cannot Transfer Into The same Account, Please select the appropriate account to perform transfer";
                redirectAttributes.addFlashAttribute("error", errorMessage);
                return "redirect:/app/dashboard";
            }

            //  CHECK FOR 0 (ZERO) VALUES:
            if(transferAmount == 0){
                errorMessage = "Cannot Transfer an amount of 0 (Zero) value, please enter a value greater than 0 (Zero) ";
                redirectAttributes.addFlashAttribute("error", errorMessage);
                return "redirect:/app/dashboard";
            }

            //  GET LOGGED IN USER:
            User user = (User) session.getAttribute("user");
            if (user == null) {
                errorMessage = "User session expired. Please log in again.";
                redirectAttributes.addFlashAttribute("error", errorMessage);
                return "redirect:/login";
            }

            //  GET CURRENT BALANCE:
            double currentBalanceOfAccountTransferringFrom  = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);

            //  CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
            if(currentBalanceOfAccountTransferringFrom < transferAmount){
                errorMessage = "You Have insufficient Funds to perform this Transfer!";
                // Log Failed Transaction:
                transactRepository.logTransaction(transferFromId, user.getUser_id(), "Transfer", transferAmount, "online", "failed", "Insufficient Funds", currentDateTime);
                redirectAttributes.addFlashAttribute("error", errorMessage);
                return "redirect:/app/dashboard";
            }

            double  currentBalanceOfAccountTransferringTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);

            //  SET NEW BALANCE:
            double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;

            double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

            // Changed The Balance Of the Account Transferring From:
            accountRepository.changeAccountBalanceById( newBalanceOfAccountTransferringFrom, transferFromId);

            // Changed The Balance Of the Account Transferring To:
            accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

            // Log Successful Transaction:
            transactRepository.logTransaction(transferFromId, user.getUser_id(), "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful",currentDateTime);

            String successMessage = "Amount Transferred Successfully!";
            redirectAttributes.addFlashAttribute("success", successMessage);
        } catch (NumberFormatException e){
            errorMessage = "Invalid account IDs or amount.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
        } catch(Exception e){
            errorMessage = "An unexpected error occurred. Please try again.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
        }
        return "redirect:/app/dashboard";
    }
    // End Of Transfer Method.

}

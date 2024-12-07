package com.example.Spring_Bank_Management_System.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_Bank_Management_System.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class RestAuthApiController {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    @GetMapping("/login")
    public ResponseEntity validateUserEmail(@PathVariable("email")String email){
        // Get User Email:
        String userEmail = userRepository.getUserEmail(email);
        // Init User Password:
        String userPassword = null;

        // Check If Email Is Validate:
        if(userEmail != null){
            userPassword = userRepository.getUserPassword(email);
            // Return Response:
            return new ResponseEntity<>(userPassword, HttpStatus.OK);
        }else{
            // Return Response:
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        // ENd Of Check If Email Is Validate.
    }
    // End f Of Validate User Login.
}
// End Of Rest Auth API Controller.

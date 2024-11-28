package com.example.Spring_Bank_Management_System.Entities;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    
    @NotEmpty(message = "The First name field cannot be empty")
    @Size(min = 3, message = "The First name field must be greater than 3 characters")
    private String first_name;
    
    @NotEmpty(message = "The Last name field cannot be empty")
    @Size(min = 3, message = "The Last name field must be greater than 3 characters")
    private String last_name;
    
    @Email
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Enter a Valid Email")
    private String email;
    
    @NotNull(message = "ID number is required to proceed")  
    private String id_num; 

    @NotNull(message = "Password cannot be empty")
    private String password;

    private String token;
    private String code;
    private int verified = 0;
    private LocalDate verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

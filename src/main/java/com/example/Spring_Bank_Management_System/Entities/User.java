package com.example.Spring_Bank_Management_System.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotEmpty(message = "The First name field cannot be empty")
    @Size(min = 3, message = "The First name field must be greater than 3 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotEmpty(message = "The Last name field cannot be empty")
    @Size(min = 3, message = "The Last name field must be greater than 3 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "ID number is required to proceed")
    @Column(name = "id_num", nullable = false, unique = true, length = 20)
    private String idNum;

    @NotNull(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name ="reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    private String token;
    private String code;
    private int verified = 0;
    private LocalDate verifiedAt;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;




    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName ) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName ) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email ) {
        this.email = email;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum ) {
        this.idNum = idNum;
    }







    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

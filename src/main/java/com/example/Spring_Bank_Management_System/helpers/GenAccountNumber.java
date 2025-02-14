package com.example.Spring_Bank_Management_System.helpers;

import java.util.Random;

public class GenAccountNumber {
    public static String generateAccountNumber() {
        Random random = new Random();
        String prefix = "AC";
        int randomNum = 100000 + random.nextInt(900000); // Generates 6-digit random number
        return prefix + randomNum; // Example: AC123456
    }
}

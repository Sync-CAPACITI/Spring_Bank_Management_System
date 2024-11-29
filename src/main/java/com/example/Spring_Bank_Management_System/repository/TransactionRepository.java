package com.example.Spring_Bank_Management_System.repository;

import com.example.Spring_Bank_Management_System.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

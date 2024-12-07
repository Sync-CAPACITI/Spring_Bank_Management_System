package com.example.Spring_Bank_Management_System.repository;

import com.example.Spring_Bank_Management_System.Entities.User;
import com.example.Spring_Bank_Management_System.Entities.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository   extends JpaRepository<Transaction, Integer> {
 
       @Query("SELECT t FROM Transaction t WHERE t.user = :user ORDER BY t.transaction_date DESC")
       List<Transaction> findByUser(@Param("user") User user);


}

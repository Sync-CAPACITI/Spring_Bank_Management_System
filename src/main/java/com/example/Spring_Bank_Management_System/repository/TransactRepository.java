package com.example.Spring_Bank_Management_System.repository;


import com.example.Spring_Bank_Management_System.Entities.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactRepository extends JpaRepository<Transact, Integer> {
    // You can add custom query methods here if needed, for example:
    // List<Transact> findByTransactionType(String transactionType);
    

}


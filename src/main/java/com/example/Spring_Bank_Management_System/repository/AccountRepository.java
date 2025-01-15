
package com.example.Spring_Bank_Management_System.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Spring_Bank_Management_System.Entities.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    @Query(value = "SELECT * FROM accounts WHERE user_id = :userId", nativeQuery = true)
    List<Account> getUserAccountsById(@Param("user_id") int userId);

    @Query(value = "SELECT sum(balance) FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    BigDecimal getTotalBalance(@Param("user_id") int userId);

    

    
    @Query(value = "SELECT * FROM accounts WHERE account_number = :account_number", nativeQuery = true)
    Account findByAccountNumber(@Param("account_number") String accountNumber);

    @Query(value = "SELECT * FROM accounts WHERE account_id = :account_id", nativeQuery = true)
    Account findAccountById(@Param("account_id") String accountId);



    @Query(value = "SELECT balance FROM accounts WHERE user_id = :user_id AND account_id = :account_id", nativeQuery = true)
    double getAccountBalance(@Param("user_id") int userId, @Param("account_id") int accountId);


    @Query(value = "SELECT account_number FROM accounts WHERE account_id = :account_id", nativeQuery = true)
    String findAccountNumber(@Param("account_id") String accountId);
    
    @Modifying
    @Query(value ="UPDATE accounts SET balance = :new_balance WHERE account_id = :account_id" , nativeQuery = true)
    @Transactional
    void changeAccountBalanceById(@Param("new_balance") double newBalance, @Param("account_id") int accountId);

    @Query(value = "SELECT * FROM accounts", nativeQuery = true)
    List<Account> getAllAccounts();

    @Modifying
    @Query(value = "INSERT INTO accounts(user_id, account_number, account_name, account_type, balance, created_at) VALUES" +
            "(:user_id, :account_number, :account_name, :account_type, :balance, :created_at)", nativeQuery = true)
    @Transactional
    void createBankAccount(@Param("user_id") int userId,
                           @Param("account_number") String accountNumber,
                           @Param("account_name") String accountName,
                           @Param("account_type") String accountType,
                           @Param("balance") BigDecimal balance,
                           @Param("created_at") LocalDateTime createdAt);
    

    @Query("SELECT a FROM Account a WHERE a.userId = :userId")
    List<Account> findByUserId(@Param("userId") int userId);


    @Modifying
    @Query(value = "UPDATE accounts SET balance = balance + :amount WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    void depositToAccount(@Param("amount") BigDecimal amount, @Param("account_id") int accountId);

    @Modifying
    @Query(value = "UPDATE accounts SET balance = balance - :amount WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    void withdrawFromAccount(@Param("amount") BigDecimal amount, 
                            @Param("account_id") int accountId);

    @Modifying
    @Query(value = "UPDATE accounts SET balance = balance + :amount WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    void transferToAccount(@Param("amount") BigDecimal amount, @Param("account_id") int accountId);
}

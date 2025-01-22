package com.example.Spring_Bank_Management_System.repository;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Spring_Bank_Management_System.Entities.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email FROM Users WHERE email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);
    
    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT password FROM Users WHERE email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    @Query(value = "SELECT verified FROM Users WHERE email = :email", nativeQuery = true)
    int isVerified(@Param("email")String email);

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email")String email);

    @Query(value = "SELECT * FROM Users WHERE reset_token = :resetToken", nativeQuery = true)
    User findByResetToken(@Param("resetToken") String resetToken);

    @Modifying
    @Query(value = "INSERT INTO Users (first_name, last_name, email, id_num, password, token, code) VALUES" +
            "(:first_name, :last_name, :email, :id_num, :password, :token, :code)", nativeQuery = true )
    @Transactional
    void registerUser(@Param("first_name")String first_name,
                      @Param("last_name") String last_name,
                      @Param("email")String email,
                      @Param("id_num")String id_num,
                      @Param("password")String password,
                      @Param("token") String token,
                      @Param("code")int code);

    @Modifying
    @Query(value = "UPDATE Users SET token=null, code=null, verified=1, verified_at=NOW(), updated_at=NOW() WHERE " +
            "token= :token AND code= :code", nativeQuery = true)
    @Transactional
    void verifyAccount(@Param("token")String token, @Param("code") String code);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.resetToken = :resetToken, u.tokenExpirationTime = :expirationTime WHERE u.email = :email")
    void updateResetToken(@Param("email") String email,
                          @Param("expirationTime") LocalDateTime expirationTime, 
                           @Param("resetToken") String resetToken
                         
                          );

   @Transactional
   @Modifying
   @Query(value = "UPDATE Users u SET u.password = :password WHERE u.reset_token = :token AND u.token_expiration_time > :currentTime", nativeQuery = true)
   int updatePasswordWithToken(@Param("token") String token,
                               @Param("password") String password,
                               @Param("currentTime") LocalDateTime currentTime);
                          
    
    @Query(value = "SELECT * FROM Users WHERE reset_token = :token", nativeQuery = true)
    User findUserByToken(@Param("token") String token);

    @Transactional
    @Query("SELECT u.resetToken FROM User u WHERE u.resetToken = :token AND u.tokenExpirationTime > :currentTime")
    String checkToken(@Param("token") String token, @Param("currentTime") LocalDateTime currentTime);

    @Query(value = "SELECT token FROM Users WHERE token = :token" , nativeQuery = true)
    String verifyToken(@Param("token")String token);

}


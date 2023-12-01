package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT U FROM User U " +
            "WHERE U.stripeClientId = :stripeClientId")
    User findByStripeClientId(@Param("stripeClientId") String stripeClientId);

    @Query("SELECT U FROM User U " +
            "WHERE U.email = :userEmail")
    User findByEmail(@Param("userEmail") String userEmail);
}
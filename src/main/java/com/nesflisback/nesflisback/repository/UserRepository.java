package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(String idUser);
}
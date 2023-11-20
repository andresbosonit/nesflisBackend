package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.Plan;
import com.nesflisback.nesflisback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
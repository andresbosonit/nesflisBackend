package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
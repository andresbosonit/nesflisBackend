package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecordRepository extends JpaRepository<Record, Integer> {

}
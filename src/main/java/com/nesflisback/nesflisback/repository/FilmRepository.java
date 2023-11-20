package com.nesflisback.nesflisback.repository;

import com.nesflisback.nesflisback.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilmRepository extends JpaRepository<Film, Integer> {

}
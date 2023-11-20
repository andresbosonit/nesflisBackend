package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.FilmOutputDTO;
import com.nesflisback.nesflisback.controller.dto.FilmInputDTO;
import com.nesflisback.nesflisback.controller.dto.FilmOutputDTO;

import java.util.List;

public interface FilmService {
    List<FilmOutputDTO> findAllFilms(int pageNumber, int pageSize);
    FilmOutputDTO getFilmById(int idFilm);
    FilmOutputDTO createFilm(FilmInputDTO filmDTO);
    void deleteFilm(int idFilm);
    void updateFilm(int idFilm, FilmInputDTO filmDTO);
}

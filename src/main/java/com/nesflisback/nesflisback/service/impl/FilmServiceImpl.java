package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.FilmInputDTO;
import com.nesflisback.nesflisback.controller.dto.FilmOutputDTO;
import com.nesflisback.nesflisback.domain.Film;
import com.nesflisback.nesflisback.domain.Plan;
import com.nesflisback.nesflisback.domain.Profile;
import com.nesflisback.nesflisback.domain.Record;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.FilmRepository;
import com.nesflisback.nesflisback.repository.RecordRepository;
import com.nesflisback.nesflisback.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    RecordRepository recordRepository;
    @Override
    public FilmOutputDTO getFilmById(int idFilm) {
        Film film = filmRepository.findById(idFilm)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la pelicula con ID: " + idFilm));
        return film.filmToFilmOutputDTO();
    }

    @Override
    public FilmOutputDTO createFilm(FilmInputDTO filmDTO) {
        return filmRepository.save(new Film(filmDTO)).filmToFilmOutputDTO();
    }

    @Override
    public void deleteFilm(int idFilm) {
        Film film = filmRepository.findById(idFilm)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la pelicula con ID: " + idFilm));
        for(Record record: film.getRecordList()){
            record.getFilmList().remove(film);
            recordRepository.save(record);
        }
        filmRepository.delete(film);
    }

    @Override
    public void updateFilm(int idFilm, FilmInputDTO filmDTO) {
        Film film = filmRepository.findById(idFilm)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la pelicula con ID: " + idFilm));
        if(filmDTO.getTitle() != null){
            film.setTitle(filmDTO.getTitle());
        }
        if(filmDTO.getOverview() != null){
            film.setOverview(filmDTO.getOverview());
        }
        if(filmDTO.getReleaseDate() != null){
            film.setReleaseDate(filmDTO.getReleaseDate());
        }
        filmRepository.save(film);
    }

    @Override
    public List<FilmOutputDTO> findAllFilms(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return filmRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Film::filmToFilmOutputDTO).toList();
    }
}


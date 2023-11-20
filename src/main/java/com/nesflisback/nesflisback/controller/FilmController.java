package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.FilmInputDTO;
import com.nesflisback.nesflisback.controller.dto.FilmOutputDTO;
import com.nesflisback.nesflisback.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllFilms(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        return ResponseEntity.ok(filmService.findAllFilms(pageNumber, pageSize));
    }


    @GetMapping("/search/{idFilm}")
    public ResponseEntity<?> searchFilmById(@PathVariable int idFilm){
        return ResponseEntity.ok(filmService.getFilmById(idFilm));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createFilm(@RequestBody FilmInputDTO filmDTO) throws URISyntaxException {
        FilmOutputDTO response = filmService.createFilm(filmDTO);
        return ResponseEntity.created(new URI("/film/create")).body(response);
    }

    @DeleteMapping("/delete/{idFilm}")
    public ResponseEntity<?> deleteFilm(@PathVariable int idFilm) throws URISyntaxException {
        filmService.deleteFilm(idFilm);
        return ResponseEntity.ok("Film deleted successfully");
    }

    @PutMapping("/update/{idFilm}")
    public ResponseEntity<?> updateFilm(@PathVariable int idFilm, @RequestBody FilmInputDTO filmDTO){
        filmService.updateFilm(idFilm, filmDTO);
        return ResponseEntity.ok("Film updated successfully");
    }
}

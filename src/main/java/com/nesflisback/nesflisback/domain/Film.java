package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.FilmInputDTO;
import com.nesflisback.nesflisback.controller.dto.FilmOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFilm;
    @Column(length = 1000)
    private String title;
    @Column(length = 1000)
    private String overview;
    private Date releaseDate;
    @ManyToMany
    @JoinColumn(name = "id_record")
    private List<Record> recordList;


    public Film(FilmInputDTO filmInputDTO){
        this.title = filmInputDTO.getTitle();
        this.overview = filmInputDTO.getOverview();
        this.releaseDate = filmInputDTO.getReleaseDate();
        this.recordList = new ArrayList<>();
    }

    public FilmOutputDTO filmToFilmOutputDTO(){
        List<Integer> integerList = new ArrayList<>();
        for(Record record: this.recordList){
            integerList.add(record.getIdRecord());
        }
        return new FilmOutputDTO(this.idFilm,this.title,this.overview,this.releaseDate,integerList);
    }
}

package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.RecordInputDTO;
import com.nesflisback.nesflisback.controller.dto.RecordOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecord;
    @OneToOne
    @JoinColumn(name = "id_profile")
    private Profile profile;
    @ManyToMany
    @JoinColumn(name = "id_film")
    private List<Film> filmList;

    public Record(){
        this.filmList = new ArrayList<>();
    }

    public RecordOutputDTO recordToRecordOutputDTO(){
        List<Integer> integerList = new ArrayList<>();
        for(Film film: this.filmList){
            integerList.add(film.getIdFilm());
        }
        return new RecordOutputDTO(this.idRecord,this.profile.getIdProfile(),integerList);
    }
}

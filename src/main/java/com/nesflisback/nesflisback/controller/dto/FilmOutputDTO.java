package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.sql.Date;
import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class FilmOutputDTO {
    private int idFilm;
    private String title;
    private String overview;
    private Date releaseDate;
    private List<Integer> recordIdList;
}

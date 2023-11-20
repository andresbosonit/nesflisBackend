package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.sql.Date;

@Value
@RequiredArgsConstructor
@Builder
public class FilmInputDTO {
    private String title;
    private String overview;
    private Date releaseDate;
}

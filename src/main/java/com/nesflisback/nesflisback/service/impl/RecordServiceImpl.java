package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.RecordOutputDTO;
import com.nesflisback.nesflisback.domain.Film;
import com.nesflisback.nesflisback.domain.Record;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.FilmRepository;
import com.nesflisback.nesflisback.repository.RecordRepository;
import com.nesflisback.nesflisback.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    FilmRepository filmRepository;
    @Override
    public RecordOutputDTO getRecordById(int idRecord) {
        Record record = recordRepository.findById(idRecord)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el historico con ID: " + idRecord));
        return record.recordToRecordOutputDTO();
    }

    @Override
    public void updateRecord(int idFilm, int idRecord) {
        Record record = recordRepository.findById(idRecord)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el historico con ID: " + idRecord));
        Film film = filmRepository.findById(idFilm)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la pelicula con ID: " + idFilm));
        film.getRecordList().add(record);
        record.getFilmList().add(film);
        filmRepository.save(film);
        recordRepository.save(record);
    }

    @Override
    public List<RecordOutputDTO> findAllRecords(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return recordRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Record::recordToRecordOutputDTO).toList();
    }
}

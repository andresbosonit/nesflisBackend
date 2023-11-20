package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.RecordInputDTO;
import com.nesflisback.nesflisback.controller.dto.RecordOutputDTO;

import java.util.List;

public interface RecordService {
    List<RecordOutputDTO> findAllRecords(int pageNumber, int pageSize);
    RecordOutputDTO getRecordById(int idRecord);
    void updateRecord(int idFilm, int idRecord);
}

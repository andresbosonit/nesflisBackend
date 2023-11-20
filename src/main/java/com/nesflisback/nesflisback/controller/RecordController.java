package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.RecordInputDTO;
import com.nesflisback.nesflisback.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllRecords(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        return ResponseEntity.ok(recordService.findAllRecords(pageNumber, pageSize));
    }

    @GetMapping("/search/{idRecord}")
    public ResponseEntity<?> searchRecordById(@PathVariable int idRecord){
        return ResponseEntity.ok(recordService.getRecordById(idRecord));
    }

    @PutMapping("/update/{idFilm}/{idRecord}")
    public ResponseEntity<?> updateRecord(@PathVariable int idFilm, @PathVariable int idRecord){
        recordService.updateRecord(idFilm, idRecord);
        return ResponseEntity.ok("Record updated successfully");
    }
}

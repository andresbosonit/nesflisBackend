package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class RecordOutputDTO {
    private int idRecord;
    private int profileId;
    private List<Integer> filmIdList;
}

package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class ProfileOutputDTO {
    private int idProfile;
    private String name;
    private byte[] image;
    private String idUser;
    private Integer idRecord;
}

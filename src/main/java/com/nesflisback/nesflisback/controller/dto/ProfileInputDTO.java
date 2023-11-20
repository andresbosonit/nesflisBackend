package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class ProfileInputDTO {
    private String name;
    private byte[] image;
    private String idUser;
}

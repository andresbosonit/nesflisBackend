package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.sql.Date;

@Value
@RequiredArgsConstructor
@Builder
public class SubscriptionInputDTO {
    private Date initDate;
    private Date terminationDate;
    private String idUser;
    private String idPlan;
}

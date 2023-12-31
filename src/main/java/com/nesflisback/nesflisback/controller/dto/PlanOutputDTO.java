package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class PlanOutputDTO {
    private String idSubType;
    private Double price;
    private String name;
    private String quality;
    private String resolution;
    private int deviceNum;
    private List<Integer> subscriptionIdList;
}

package com.nesflisback.nesflisback.controller.dto;

import com.nesflisback.nesflisback.domain.Subscription;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class PlanInputDTO {
    private Double price;
    private String name;
}

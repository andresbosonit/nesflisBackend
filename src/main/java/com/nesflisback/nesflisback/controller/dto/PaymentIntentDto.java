package com.nesflisback.nesflisback.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentIntentDto {
    private enum Currency{
        USD, EUR;
    }
    private String description;
    private int amount;
    private Currency currency;
    private UserOutputDTO userOutputDTO;
}


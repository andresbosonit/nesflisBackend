package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.MessageResponseDto;
import com.nesflisback.nesflisback.service.impl.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/createLink/{productId}")
    public ResponseEntity<MessageResponseDto> payment (@PathVariable String productId) {
        return new ResponseEntity<>(new MessageResponseDto(paymentService.createPaymentLink(productId)), HttpStatus.OK);
    }
}

package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.PaymentIntentDto;
import com.nesflisback.nesflisback.service.impl.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> payment () throws StripeException {
        Subscription subscription = paymentService.subscribe();
        String subscriptionJson = subscription.toJson();
        return new ResponseEntity<>(subscriptionJson, HttpStatus.OK);
    }
}

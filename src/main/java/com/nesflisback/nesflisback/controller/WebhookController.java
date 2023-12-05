package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.service.impl.WebhookService;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    @Autowired
    WebhookService webhookService;

    private String webhookSecret = "whsec_004c927dc37e85680bf5dd3a0e93daab7195fcb003a321d473d57f2166b18ee2";

    @PostMapping
    public void handleStripeWebhook(@RequestBody String payload, HttpServletRequest request) throws StripeException {
            String sigHeader = request.getHeader("Stripe-Signature");
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            webhookService.handleCustomerCreatedEvent(event);
    }
}

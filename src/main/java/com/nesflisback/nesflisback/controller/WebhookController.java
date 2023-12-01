package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.domain.User;
import com.nesflisback.nesflisback.repository.PlanRepository;
import com.nesflisback.nesflisback.repository.SubscriptionRepository;
import com.nesflisback.nesflisback.repository.UserRepository;
import com.nesflisback.nesflisback.service.UserService;
import com.nesflisback.nesflisback.service.impl.PaymentService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    PlanRepository planRepository;

    private String webhookSecret = "whsec_004c927dc37e85680bf5dd3a0e93daab7195fcb003a321d473d57f2166b18ee2"; // Obtén esta clave desde tu panel de control de Stripe

    @PostMapping
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, HttpServletRequest request) {
        try {
            String sigHeader = request.getHeader("Stripe-Signature");
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            StripeObject stripeObject = event.getData().getObject();

            switch (event.getType()) {
                case "customer.created":
                    if (stripeObject instanceof Customer customer) {
                        User user = userRepository.findByEmail(customer.getEmail());
                        user.setStripeClientId(customer.getId());
                        System.out.println("CustomerID: " + customer.getId());
                        userRepository.save(user);
                    }
                    break;
//                case "checkout.session.completed":
//                    if (stripeObject instanceof Session session){
//                        Subscription subscription = paymentService.getSubscription(session.getSubscription());
//                        com.nesflisback.nesflisback.domain.Subscription mySubscription = new com.nesflisback.nesflisback.domain.Subscription();
//                        mySubscription.setInitDate(new Date(subscription.getStartDate()*1000));
//                        mySubscription.setTerminationDate(new Date(subscription.getCurrentPeriodEnd()*1000));
//
//                        User user = userRepository.findByStripeClientId(subscription.getCustomer());
//
//                        mySubscription.setUser(user);
//                        subscriptionRepository.save(mySubscription);
//
//                        user.setSubscription(mySubscription);
//                        userRepository.save(user);
//                    }
//                    break;
            }

            return new ResponseEntity<>("Webhook received successfully", HttpStatus.OK);

        } catch (SignatureVerificationException e) {
            e.printStackTrace(); // Maneja los errores de verificación de firma y de IO
            return new ResponseEntity<>("Webhook signature verification failed", HttpStatus.BAD_REQUEST);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.PaymentIntentDto;
import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;
import com.nesflisback.nesflisback.service.SubscriptionService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${spring.stripe.key.private}")
    String secretKey;

    @Autowired
    SubscriptionService subscriptionService;


    public Customer createCustomer(UserOutputDTO userOutputDTO) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("id", userOutputDTO.getIdUser());
        customerParams.put("email", userOutputDTO.getEmail());
        customerParams.put("name", userOutputDTO.getFirstName() + " " + userOutputDTO.getLastName());

        return Customer.create(customerParams);
    }
    public Subscription subscribe() throws StripeException {
        Stripe.apiKey = secretKey;

        List<Object> items = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put(
                "price",
                "price_1OFYU8GAd0Yb1G0RRGU5GjSQ"
        );
        items.add(item1);
        Map<String, Object> params = new HashMap<>();
        params.put("customer", "cus_P3hL6ATfyFbhen");
        params.put("items", items);

        Subscription subscription =
                Subscription.create(params);

        return subscription;
    }
}

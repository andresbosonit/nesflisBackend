package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.service.SubscriptionService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentLinkCreateParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class PaymentService {

    @Value("${spring.stripe.key.private}")
    String secretKey;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ModelMapper modelMapper;

    public String createPaymentLink(String productId) {
        try {
            Stripe.apiKey = secretKey;

            PaymentLinkCreateParams.LineItem item = PaymentLinkCreateParams.LineItem.builder()
                    .setPrice(productId)
                    .setQuantity(1L)
                    .build();

            PaymentLinkCreateParams.AfterCompletion afterCompletion = PaymentLinkCreateParams.AfterCompletion.builder()
                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                            .setUrl("http://localhost:4200/home")
                            .build())
                    .build();

            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addLineItem(item)
                    .setAfterCompletion(afterCompletion)
                    .build();

            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


            String encodedEmail = URLEncoder.encode(jwt.getClaimAsString("email"), StandardCharsets.UTF_8.toString());

            PaymentLink paymentLink = PaymentLink.create(params);
            paymentLink.setUrl(paymentLink.getUrl()+"?prefilled_email=" + encodedEmail);
            System.out.println(paymentLink.getUrl());
            return paymentLink.getUrl();
        } catch (StripeException e) {
            e.printStackTrace();
            return "Error al crear el enlace de pago";
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Subscription getSubscription(String subId) throws StripeException {
        return Subscription.retrieve(subId);
    }
}

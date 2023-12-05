package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.domain.User;
import com.nesflisback.nesflisback.repository.PlanRepository;
import com.nesflisback.nesflisback.repository.SubscriptionRepository;
import com.nesflisback.nesflisback.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class WebhookService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    PlanRepository planRepository;


    public void handleCustomerCreatedEvent (Event event) throws StripeException {
        StripeObject stripeObject = event.getData().getObject();

        if ("checkout.session.completed".equals(event.getType())) {
            if (stripeObject instanceof Session session) {
                System.out.println(session.toString());
                User user = userRepository.findByEmail(session.getCustomerDetails().getEmail());
                user.setStripeClientId(session.getCustomer());
                Subscription subscription = paymentService.getSubscription(session.getSubscription());
                com.nesflisback.nesflisback.domain.Subscription mySubscription = new com.nesflisback.nesflisback.domain.Subscription();
                mySubscription.setInitDate(new Date(subscription.getStartDate() * 1000));
                mySubscription.setTerminationDate(new Date(subscription.getCurrentPeriodEnd() * 1000));

                com.nesflisback.nesflisback.domain.Plan plan = planRepository.findById(subscription.getItems().getData().get(0).getPlan().getId()).orElseThrow();
                Customer customer = Customer.retrieve(subscription.getCustomer());

                if (user != null) {
                    mySubscription.setPlan(plan);
                    user.setSubscription(mySubscription);
                    mySubscription.setUser(user);
                    subscriptionRepository.save(mySubscription);
                    userRepository.save(user);

                } else {
                    // Manejar el caso en que no se encuentra el usuario
                    System.out.println("Usuario no encontrado para stripeClientId: " + subscription.getCustomer());
                }
            }
        }
    }
}

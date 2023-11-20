package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.SubscriptionInputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionOutputDTO;
import com.nesflisback.nesflisback.domain.Plan;
import com.nesflisback.nesflisback.domain.Subscription;
import com.nesflisback.nesflisback.domain.User;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.PlanRepository;
import com.nesflisback.nesflisback.repository.SubscriptionRepository;
import com.nesflisback.nesflisback.repository.UserRepository;
import com.nesflisback.nesflisback.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlanRepository planRepository;

    @Override
    public SubscriptionOutputDTO getSubscriptionById(int idSub) {
        Subscription subscription = subscriptionRepository.findById(idSub)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la subscripción con ID: " + idSub));
        return subscription.subToSubOutputDto();
    }

    @Override
    public SubscriptionOutputDTO createSubscription(SubscriptionInputDTO subscriptionDTO) {
        Subscription subscription = new Subscription(subscriptionDTO);
        User user = userRepository.findById(subscriptionDTO.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + subscriptionDTO.getIdUser()));
        user.setSubscription(subscription);
        subscription.setUser(user);
        Plan plan = planRepository.findById(subscriptionDTO.getIdPlan())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el plan con ID: " + subscriptionDTO.getIdUser()));
        plan.getSubscriptionList().add(subscription);
        subscription.setPlan(plan);
        SubscriptionOutputDTO subscriptionOutputDTO = subscriptionRepository.save(subscription).subToSubOutputDto();
        planRepository.save(plan);
        userRepository.save(user);
        return subscriptionOutputDTO;
    }

    @Override
    public void updateSubscription(int idSub, SubscriptionInputDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(idSub)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la subscripción con ID: " + idSub));
        if(subscriptionDTO.getInitDate() != null){
            subscription.setInitDate(subscriptionDTO.getInitDate());
        }
        if(subscriptionDTO.getTerminationDate() != null){
            subscription.setTerminationDate(subscriptionDTO.getTerminationDate());
        }
        if(subscriptionDTO.getIdPlan() != null){
            Plan oldPlan = planRepository.findById(subscriptionDTO.getIdPlan())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el plan con ID: " + subscriptionDTO.getIdUser()));
            Plan newPlan = planRepository.findById(subscriptionDTO.getIdPlan())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el plan con ID: " + subscriptionDTO.getIdUser()));
            oldPlan.getSubscriptionList().remove(subscription);
            newPlan.getSubscriptionList().add(subscription);
            subscription.setPlan(newPlan);
            planRepository.save(oldPlan);
            planRepository.save(newPlan);
        }
        subscriptionRepository.save(subscription).subToSubOutputDto();
    }

    @Override
    public List<SubscriptionOutputDTO> findAllSubscriptions(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subscriptionRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Subscription::subToSubOutputDto).toList();
    }
}


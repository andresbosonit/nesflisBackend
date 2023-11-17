package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.SubscriptionInputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionOutputDTO;
import com.nesflisback.nesflisback.controller.dto.UserInputDTO;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionOutputDTO> findAllSubscriptions(int pageNumber, int pageSize);
    SubscriptionOutputDTO getSubscriptionById(int idSub);
    SubscriptionOutputDTO createSubscription(SubscriptionInputDTO subscriptionDTO);
    void updateSubscription(int idSub, SubscriptionInputDTO subscriptionDTO);
}

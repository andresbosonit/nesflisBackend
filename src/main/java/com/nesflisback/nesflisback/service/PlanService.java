package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionInputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionOutputDTO;

import java.util.List;

public interface PlanService {
    List<PlanOutputDTO> findAllPlans(int pageNumber, int pageSize);
    PlanOutputDTO getPlanById(String idPlan);
    PlanOutputDTO createPlan(PlanInputDTO planDTO);
    void updatePlan(String idPlan, PlanInputDTO planDTO);
}

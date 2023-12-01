package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import com.nesflisback.nesflisback.domain.Plan;
import com.nesflisback.nesflisback.domain.Subscription;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.PlanRepository;
import com.nesflisback.nesflisback.repository.SubscriptionRepository;
import com.nesflisback.nesflisback.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    PlanRepository planRepository;
    @Override
    public PlanOutputDTO getPlanById(String idPlan) {
        Plan plan = planRepository.findById(idPlan)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el plan con ID: " + idPlan));
        return plan.planToPlanOutputDto();
    }

    @Override
    public PlanOutputDTO createPlan(PlanInputDTO planDTO) {
        return planRepository.save(new Plan(planDTO)).planToPlanOutputDto();
    }

    @Override
    public void updatePlan(String idPlan, PlanInputDTO planDTO) {
        Plan plan = planRepository.findById(idPlan)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el plan con ID: " + idPlan));
        if(planDTO.getName() != null){
            plan.setName(planDTO.getName());
        }
        if(planDTO.getPrice() != null){
            plan.setPrice(planDTO.getPrice());
        }
        planRepository.save(plan);
    }

    @Override
    public List<PlanOutputDTO> findAllPlans(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return planRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Plan::planToPlanOutputDto).toList();
    }
}

package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.nesflisback.nesflisback.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "*")
public class PlanController {
    @Autowired
    PlanService planService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllPlans(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(jwt.getClaim("sid").toString());

        return ResponseEntity.ok(planService.findAllPlans(pageNumber, pageSize));
    }


    @GetMapping("/search/{idPlan}")
    public ResponseEntity<?> searchPlanById(@PathVariable String idPlan){
        return ResponseEntity.ok(planService.getPlanById(idPlan));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPlan(@RequestBody PlanInputDTO planDTO) throws URISyntaxException {
        PlanOutputDTO response = planService.createPlan(planDTO);
        return ResponseEntity.created(new URI("/plan/create")).body(response);
    }


    @PutMapping("/update/{planId}")
    public ResponseEntity<?> updatePlan(@PathVariable String planId, @RequestBody PlanInputDTO planDTO){
        planService.updatePlan(planId, planDTO);
        return ResponseEntity.ok("Plan updated successfully");
    }
}

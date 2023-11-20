package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionInputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionOutputDTO;
import com.nesflisback.nesflisback.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/sub")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllSubscriptions(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        return ResponseEntity.ok(subscriptionService.findAllSubscriptions(pageNumber, pageSize));
    }


    @GetMapping("/search/{idSub}")
    public ResponseEntity<?> searchSubscriptionById(@PathVariable int idSub){
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(idSub));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionInputDTO subDTO) throws URISyntaxException {
        SubscriptionOutputDTO response = subscriptionService.createSubscription(subDTO);
        return ResponseEntity.created(new URI("/sub/create")).body(response);
    }


    @PutMapping("/update/{idSub}")
    public ResponseEntity<?> updateSubscription(@PathVariable int idSub, @RequestBody SubscriptionInputDTO subDTO){
        subscriptionService.updateSubscription(idSub, subDTO);
        return ResponseEntity.ok("Subscription updated successfully");
    }
}

package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    private String idSubType;
    private Double price;
    private String name;
    private String quality;
    private String resolution;
    private int deviceNum;
    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscriptionList;

    public Plan(PlanInputDTO planDTO){
        this.price = planDTO.getPrice();
        this.name = planDTO.getName();
        this.quality = planDTO.getQuality();
        this.resolution = planDTO.getQuality();
        this.deviceNum = planDTO.getDeviceNum();
        this.subscriptionList = new ArrayList<>();
    }
    public PlanOutputDTO planToPlanOutputDto(){
        List<Integer> aux = new ArrayList<>();
        for(Subscription subscription: this.subscriptionList){
            aux.add(subscription.getIdSub());
        }
        return new PlanOutputDTO(this.idSubType,this.price,this.name,this.quality,this.resolution,this.deviceNum,aux);
    }
}

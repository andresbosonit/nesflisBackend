package com.nesflisback.nesflisback.domain;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubType;
    private Double price;
    private String name;
    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscriptionList;

    public PlanOutputDTO planToPlanOutputDto(){
        List<Integer> aux = new ArrayList<>();
        for(Subscription subscription: this.subscriptionList){
            aux.add(subscription.getIdSub());
        }
        return new PlanOutputDTO(this.idSubType,this.price,this.name,aux);
    }
}

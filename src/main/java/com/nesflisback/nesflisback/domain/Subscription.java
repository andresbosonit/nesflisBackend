package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.SubscriptionInputDTO;
import com.nesflisback.nesflisback.controller.dto.SubscriptionOutputDTO;
import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSub;
    private Date initDate;
    private Date terminationDate;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_sub_type")
    private Plan plan;

    public Subscription(SubscriptionInputDTO subscriptionInputDTO){
        this.initDate = subscriptionInputDTO.getInitDate();
        this.terminationDate = subscriptionInputDTO.getTerminationDate();
    }
    public SubscriptionOutputDTO subToSubOutputDto(){
        return new SubscriptionOutputDTO(this.idSub,this.initDate,this.terminationDate,this.user.getIdUser(),this.plan.getIdSubType());
    }
}

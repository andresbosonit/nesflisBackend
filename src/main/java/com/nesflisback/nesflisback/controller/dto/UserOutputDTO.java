package com.nesflisback.nesflisback.controller.dto;

import com.nesflisback.nesflisback.domain.Profile;
import com.nesflisback.nesflisback.domain.Subscription;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO implements Serializable{
    private String idUser;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean emailVerified;
    private Date unsubscribeDate;
    private Integer subscriptionId;
    private List<Integer> profileIdList;

    public UserOutputDTO(String uuid) {
        this.idUser = uuid;
    }
}

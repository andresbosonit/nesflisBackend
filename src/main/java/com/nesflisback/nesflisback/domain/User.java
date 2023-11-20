package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String idUser;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean emailVerified;
    private Date unsubscribeDate;
    @OneToOne
    @JoinColumn(name = "id_sub")
    private Subscription subscription;
    @OneToMany(mappedBy = "user")
    private List<Profile> profileList;

    public User(UserInputDTO userInputDTO){
        this.idUser = userInputDTO.getIdUser();
        this.username = userInputDTO.getUsername();
        this.email = userInputDTO.getEmail();
        this.firstName = userInputDTO.getFirstName();
        this.lastName = userInputDTO.getLastName();
        this.password = userInputDTO.getPassword();
        this.emailVerified = userInputDTO.getEmailVerified();
        this.unsubscribeDate = null;
        this.subscription = null;
        this.profileList = new ArrayList<>();
    }
    public UserOutputDTO userToUserOutputDto(){
        List<Integer> integerList = new ArrayList<>();
        for(Profile profile: this.getProfileList()){
            integerList.add(profile.getIdProfile());
        }
        Integer aux = null;
        if(this.subscription != null){
            aux = this.subscription.getIdSub();
        }
        return new UserOutputDTO(this.idUser,this.username,this.email,this.firstName,this.lastName,this.password,this.emailVerified,this.unsubscribeDate,aux,integerList);
    }
}

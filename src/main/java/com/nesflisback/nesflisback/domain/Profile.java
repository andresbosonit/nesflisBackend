package com.nesflisback.nesflisback.domain;

import com.nesflisback.nesflisback.controller.dto.PlanInputDTO;
import com.nesflisback.nesflisback.controller.dto.PlanOutputDTO;
import com.nesflisback.nesflisback.controller.dto.ProfileInputDTO;
import com.nesflisback.nesflisback.controller.dto.ProfileOutputDTO;
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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfile;
    private String name;
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToOne
    @JoinColumn(name = "id_record")
    private Record record;

    public Profile(ProfileInputDTO profileDTO){
        this.name = profileDTO.getName();
        this.image = profileDTO.getImage();
    }
    public ProfileOutputDTO profileToProfileOutputDto(){
        return new ProfileOutputDTO(this.idProfile,this.name,this.image,this.user.getIdUser(),this.record.getIdRecord());
    }
}

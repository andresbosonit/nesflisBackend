package com.nesflisback.nesflisback.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean emailVerified;
    @OneToOne
    @JoinColumn(name = "id_sub")
    private Subscription subscription;
    @OneToMany(mappedBy = "user")
    private List<Profile> profileList;
}

package com.nesflisback.nesflisback.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private double price;
    private String name;
    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscriptionList;
}

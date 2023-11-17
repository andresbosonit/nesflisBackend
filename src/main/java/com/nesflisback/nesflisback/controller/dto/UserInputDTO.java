package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
@Value
@RequiredArgsConstructor
@Builder
public class UserInputDTO implements Serializable{
    private String idUser;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean emailVerified;
    private Date unsubscribeDate;
}

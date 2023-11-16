package com.nesflisback.nesflisback.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@RequiredArgsConstructor
@Builder
public class UserOutputDTO implements Serializable{
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<String> roles;
}

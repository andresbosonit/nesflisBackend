package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.util.Message;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @GetMapping("/hello-1")
    @PreAuthorize("hasRole('admin_client_role')")
    public Message helloAdmin(){
        return new Message("Hello Sprig Boot With Keycloak with USER");
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('user_client_role') or hasRole('client_admin')")
    public String helloUser(){
        return "Hello Sprig Boot With Keycloak with USER";
    }
}

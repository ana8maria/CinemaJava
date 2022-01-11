package com.proiect.cinemamanagement.rest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cm/api/v1")
public class UserResource {

    @GetMapping("/user")
    public String helloUser(){
        return "Welcome to Springboot Security tutorial";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String securedUser(){
        return "Welcome admin to this tutorial ";

    }
}
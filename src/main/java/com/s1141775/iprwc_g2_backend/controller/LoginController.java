package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.LoginCredentials;
import com.s1141775.iprwc_g2_backend.service.AuthenticationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping ("/login")
    private String login(LoginCredentials loginCredentials){
        System.out.println("login()");
        return authenticationService.signUp(loginCredentials);
    }
}

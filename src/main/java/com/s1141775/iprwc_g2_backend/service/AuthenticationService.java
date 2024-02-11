package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.model.LoginCredentials;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService
{
    private final JWTService jwtService;

    public AuthenticationService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    public String signUp(LoginCredentials loginCredentials){
        System.out.println("signUp()");

        String jwtToken = jwtService.generateFromUsername(loginCredentials.username);
        System.out.println(jwtToken);

        return jwtToken;
    }


}

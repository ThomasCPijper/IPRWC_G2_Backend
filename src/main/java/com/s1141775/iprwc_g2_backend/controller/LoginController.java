package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountType;
import com.s1141775.iprwc_g2_backend.model.LoginCredentials;
import com.s1141775.iprwc_g2_backend.model.RegisterCredentials;
import com.s1141775.iprwc_g2_backend.service.AccountService;
import com.s1141775.iprwc_g2_backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController
{
    private final AuthenticationService authenticationService;
    private final AccountService accountService;

    public LoginController(AuthenticationService authenticationService, AccountService accountService) {
        this.authenticationService = authenticationService;
        this.accountService = accountService;
    }

    @PostMapping("/register")
    private ResponseEntity<String> register(RegisterCredentials registerCredentials){
        try{
            //Create account
            Account newAccount = new Account(registerCredentials.username, registerCredentials.password, registerCredentials.email, AccountType.Customer);
            this.accountService.save(newAccount);

            //Return JWT-token
            LoginCredentials loginCredentials = new LoginCredentials(registerCredentials.username, registerCredentials.password);
            String JWTToken = this.authenticationService.signUp(loginCredentials);
            return ResponseEntity.ok(JWTToken);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping ("/login")
    private ResponseEntity<String> login(LoginCredentials loginCredentials){
        if(!checkIfAccountExists(loginCredentials.username)){
            return ResponseEntity.notFound().build();
        }
        String JWTToken = authenticationService.signUp(loginCredentials);
        return ResponseEntity.ok(JWTToken);
    }

    private boolean checkIfAccountExists(String username){
        if(accountService.findById(username).isPresent()){
            return true;
        }
        return false;
    }
}

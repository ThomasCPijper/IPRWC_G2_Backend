package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountType;
import com.s1141775.iprwc_g2_backend.model.LoginCredentials;
import com.s1141775.iprwc_g2_backend.model.RegisterCredentials;
import com.s1141775.iprwc_g2_backend.service.AccountService;
import com.s1141775.iprwc_g2_backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<String> register(@RequestBody RegisterCredentials registerCredentials){
        //Check if username is in use
        if(checkIfAccountExists(registerCredentials.username)){
            return ResponseEntity.badRequest().body("Username in use");
        }

        try{
            //Create account
            Account account = new Account(registerCredentials.username, registerCredentials.password, registerCredentials.email, AccountType.Customer);
            this.accountService.save(account);

            //Return JWT-token
            LoginCredentials loginCredentials = new LoginCredentials(registerCredentials.username, registerCredentials.password);
            String JWTToken = this.authenticationService.signUp(loginCredentials);
            return ResponseEntity.ok(JWTToken);

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error during creation of account");
        }
    }

    @PostMapping ("/login")
    private ResponseEntity<String> login(@RequestBody LoginCredentials credentials){
        //Check if account exists
        if(!checkIfAccountExists(credentials.username)){
            return ResponseEntity.badRequest().build();
        }
        //Check if password is correct
        if(!checkIfPasswordIsCorrect(credentials)){
            return ResponseEntity.badRequest().build();
        }
        String JWTToken = authenticationService.signUp(credentials);
        return ResponseEntity.ok(JWTToken);
    }

    private boolean checkIfAccountExists(String username){
        return accountService.findByName(username).isPresent();
    }

    private boolean checkIfPasswordIsCorrect(LoginCredentials credentials){
        if(this.accountService.findByName(credentials.username).isPresent()){
            String password = this.accountService.findByName(credentials.username).get().getPassword();
            return password.equals(credentials.password);
        }
        return false;
    }
}

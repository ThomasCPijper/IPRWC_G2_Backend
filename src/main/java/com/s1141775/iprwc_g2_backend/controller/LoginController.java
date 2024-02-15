package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.*;
import com.s1141775.iprwc_g2_backend.service.AccountService;
import com.s1141775.iprwc_g2_backend.service.AuthenticationService;
import com.s1141775.iprwc_g2_backend.service.JwtDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController
{
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final JwtDtoService jwtDtoService;

    public LoginController(AuthenticationService authenticationService, AccountService accountService, JwtDtoService jwtDtoService) {
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.jwtDtoService = jwtDtoService;
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
            String JWTToken = this.authenticationService.signUp();
            AccountJWTDTO accountJwtDto = new AccountJWTDTO(account, JWTToken);
            this.jwtDtoService.save(accountJwtDto);
            return ResponseEntity.ok(JWTToken);

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error during creation of account");
        }
    }

    @PostMapping ("/login")
    private ResponseEntity<String> login(@RequestBody LoginCredentials credentials){
        //Check if account exists
        if(!checkIfAccountExists(credentials.username)){
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }
        //Check if password is correct
        if(!checkIfPasswordIsCorrect(credentials)){
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }

        deleteOldToken(this.accountService.findByName(credentials.username).get());

        String JWTToken = authenticationService.signUp();
        Account account = this.accountService.findByName(credentials.username).get();
        AccountJWTDTO accountJwtDto = new AccountJWTDTO(account, JWTToken);
        this.jwtDtoService.save(accountJwtDto);

        return ResponseEntity.ok(JWTToken);
    }

    private void deleteOldToken(Account account){
        if(this.jwtDtoService.getByAccount(account) == null){
            return;
        }
        AccountJWTDTO accountJWTDTO = this.jwtDtoService.getByAccount(account);
        this.jwtDtoService.delete(accountJWTDTO);
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

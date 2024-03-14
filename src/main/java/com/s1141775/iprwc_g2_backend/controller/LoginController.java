package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.*;
import com.s1141775.iprwc_g2_backend.service.AccountService;
import com.s1141775.iprwc_g2_backend.service.AuthenticationService;
import com.s1141775.iprwc_g2_backend.service.JwtDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

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
            Account account = new Account();
            account.setUsername(registerCredentials.username);

            String salt = generateRandomSalt();
            String hashedPassword = hashPassword(registerCredentials.getPassword(), salt);
            System.out.println(registerCredentials.getPassword());
            System.out.println(hashedPassword);

            account.setPassword(hashedPassword);
            account.setSalt(salt);
            System.out.println(salt);
            account.setEmail(registerCredentials.email);
            account.setType(AccountType.Customer);
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

        //Create token
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
        Optional<Account> optionalAccount = this.accountService.findByName(credentials.username);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            String hashedPassword = hashPassword(credentials.password, account.getSalt());
            return account.getPassword().equals(hashedPassword);
        }
        return false;
    }

    private String generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String password, String salt) {
        String saltedPassword = password + salt;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException stacktrace) {
            throw new RuntimeException("Error hashing password", stacktrace);
        }
    }
}

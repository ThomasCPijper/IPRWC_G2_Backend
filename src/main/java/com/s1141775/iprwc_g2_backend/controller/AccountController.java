package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountType;
import com.s1141775.iprwc_g2_backend.service.AccountService;
import com.s1141775.iprwc_g2_backend.service.JwtDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AccountController {
    private final AccountService accountService;
    private final JwtDtoService jwtDtoService;

    public AccountController(AccountService accountService, JwtDtoService jwtDtoService) {
        this.accountService = accountService;
        this.jwtDtoService = jwtDtoService;
    }

    @PostMapping("/checkuser")
    private ResponseEntity<String> checkUser(@RequestBody String token){
        System.out.println("checkUser()");
        if(this.jwtDtoService.getByJwtToken(token).isPresent()){
            Account account = this.jwtDtoService.getByJwtToken(token).get().getAccount();
            if (account.getType().equals(AccountType.Admin)){
                System.out.println("Admin");
                return ResponseEntity.ok().body("Admin");
            } else if (account.getType().equals(AccountType.Customer)) {
                System.out.println("Customer");
                return ResponseEntity.badRequest().body("Customer");
            } else {
                System.out.println("Customer");
                return ResponseEntity.badRequest().body("Customer");
            }
        } else {
            System.out.println("Customer");
            return ResponseEntity.badRequest().body("Customer");
        }
    }
}

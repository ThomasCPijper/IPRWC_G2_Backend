package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


}

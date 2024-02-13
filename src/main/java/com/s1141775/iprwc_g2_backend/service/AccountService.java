package com.s1141775.iprwc_g2_backend.service;

import org.springframework.stereotype.Service;
import com.s1141775.iprwc_g2_backend.model.Account;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    Account save(Account account);

    List<Account> findAll();

    Optional<Account> findById(String id);

    Optional<Account> findByName(String username);

    void delete(Account account);
}

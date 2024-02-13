package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.AccountRepository;
import com.s1141775.iprwc_g2_backend.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id);
    }

    @Override
    public Optional<Account> findByName(String username) {
        return this.accountRepository.findByUsername(username);
    }

    @Override
    public void delete(Account account) {
        this.accountRepository.delete(account);
    }
}

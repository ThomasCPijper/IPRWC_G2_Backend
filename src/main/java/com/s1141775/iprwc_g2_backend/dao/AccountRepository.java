package com.s1141775.iprwc_g2_backend.dao;

import com.s1141775.iprwc_g2_backend.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}

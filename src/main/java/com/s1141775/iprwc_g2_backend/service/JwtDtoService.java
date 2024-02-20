package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface JwtDtoService {
    public AccountJWTDTO save(AccountJWTDTO accountJWTDTO);

    public AccountJWTDTO getByAccount(Account account);

    public Optional<AccountJWTDTO> getByJwtToken(String jwtToken);

    public void delete(AccountJWTDTO accountJWTDTO);
}

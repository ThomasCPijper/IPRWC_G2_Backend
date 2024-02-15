package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;
import org.springframework.stereotype.Service;

@Service
public interface JwtDtoService {
    public AccountJWTDTO save(AccountJWTDTO accountJWTDTO);

    public void delete(AccountJWTDTO accountJWTDTO);
}

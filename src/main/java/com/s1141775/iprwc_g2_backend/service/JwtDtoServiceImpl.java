package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.JwtDtoRepository;
import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;
import org.springframework.stereotype.Service;

@Service
public class JwtDtoServiceImpl implements JwtDtoService{
    private final JwtDtoRepository jwtDtoRepository;

    public JwtDtoServiceImpl(JwtDtoRepository jwtDtoRepository) {
        this.jwtDtoRepository = jwtDtoRepository;
    }

    @Override
    public AccountJWTDTO save(AccountJWTDTO accountJWTDTO) {
        return this.jwtDtoRepository.save(accountJWTDTO);
    }

    @Override
    public AccountJWTDTO getByAccount(Account account) {
        return this.jwtDtoRepository.getByAccount(account);
    }

    @Override
    public AccountJWTDTO getByJwtToken(String jwtToken) {
        return this.jwtDtoRepository.getByJwtToken(jwtToken);
    }

    @Override
    public void delete(AccountJWTDTO accountJWTDTO) {
        this.jwtDtoRepository.delete(accountJWTDTO);
    }
}

package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.JwtDtoRepository;
import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;

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
    public void delete(AccountJWTDTO accountJWTDTO) {
        this.jwtDtoRepository.delete(accountJWTDTO);
    }
}

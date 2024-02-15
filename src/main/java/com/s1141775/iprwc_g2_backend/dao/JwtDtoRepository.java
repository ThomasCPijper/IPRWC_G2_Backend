package com.s1141775.iprwc_g2_backend.dao;

import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtDtoRepository extends CrudRepository<AccountJWTDTO, String> {
}

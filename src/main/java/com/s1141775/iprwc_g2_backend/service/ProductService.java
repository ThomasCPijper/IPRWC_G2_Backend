package com.s1141775.iprwc_g2_backend.service;


import com.s1141775.iprwc_g2_backend.model.BackendProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    BackendProduct save(BackendProduct product);

    List<BackendProduct> findAll();

    Optional<BackendProduct> findById(String id);

    void delete(BackendProduct product);
}

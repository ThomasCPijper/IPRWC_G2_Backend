package com.s1141775.iprwc_g2_backend.service;


import com.s1141775.iprwc_g2_backend.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(String id);

    void delete(Product product);
}

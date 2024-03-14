package com.s1141775.iprwc_g2_backend.dao;

import com.s1141775.iprwc_g2_backend.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}

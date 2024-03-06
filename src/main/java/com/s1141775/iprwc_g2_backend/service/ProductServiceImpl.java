package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.ProductRepository;
import com.s1141775.iprwc_g2_backend.model.BackendProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BackendProduct save(BackendProduct product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<BackendProduct> findAll() {
        return (List<BackendProduct>) this.productRepository.findAll();
    }

    @Override
    public Optional<BackendProduct> findById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void delete(BackendProduct product) {
        this.productRepository.delete(product);
    }
}

package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.ProductInShoppingCartRepository;
import com.s1141775.iprwc_g2_backend.dao.ProductRepository;
import com.s1141775.iprwc_g2_backend.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductInShoppingCartRepository productInShoppingCartRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }
}

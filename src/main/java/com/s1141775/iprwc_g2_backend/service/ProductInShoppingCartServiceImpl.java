package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.ProductInShoppingCartRepository;
import com.s1141775.iprwc_g2_backend.model.ProductInShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInShoppingCartServiceImpl implements ProductInShoppingCartService {
    private final ProductInShoppingCartRepository productInShoppingCartRepository;

    public ProductInShoppingCartServiceImpl(ProductInShoppingCartRepository productInShoppingCartRepository) {
        this.productInShoppingCartRepository = productInShoppingCartRepository;
    }

    @Override
    public ProductInShoppingCart save(ProductInShoppingCart productInShoppingCart) {
        return this.productInShoppingCartRepository.save(productInShoppingCart);
    }

    @Override
    public List<ProductInShoppingCart> findAll() {
        return (List<ProductInShoppingCart>) this.productInShoppingCartRepository.findAll();
    }

    @Override
    public List<ProductInShoppingCart> findByOrderId(String orderId) {
        return (List<ProductInShoppingCart>) this.productInShoppingCartRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<ProductInShoppingCart> findById(String id) {
        return this.productInShoppingCartRepository.findById(id);
    }

    @Override
    public void delete(ProductInShoppingCart productInShoppingCart) {
        this.productInShoppingCartRepository.delete(productInShoppingCart);
    }
}

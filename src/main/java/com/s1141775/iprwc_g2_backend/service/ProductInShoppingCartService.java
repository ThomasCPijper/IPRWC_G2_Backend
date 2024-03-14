package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.ProductInShoppingCartRepository;
import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import com.s1141775.iprwc_g2_backend.model.ProductInShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductInShoppingCartService {
    ProductInShoppingCart save(ProductInShoppingCart productInShoppingCart);

    List<ProductInShoppingCart> findAll();

    List<ProductInShoppingCart> findByOrderId(String orderId);

    Optional<ProductInShoppingCart> findById(String id);

    void delete(ProductInShoppingCart productInShoppingCart);

}

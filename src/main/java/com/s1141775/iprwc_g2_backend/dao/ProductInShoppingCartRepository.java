package com.s1141775.iprwc_g2_backend.dao;

import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import com.s1141775.iprwc_g2_backend.model.ProductInShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInShoppingCartRepository extends CrudRepository<ProductInShoppingCart, String> {
    List<ProductInShoppingCart> findByOrderId(String order_id);
}

package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import com.s1141775.iprwc_g2_backend.model.FrontendOrder;
import com.s1141775.iprwc_g2_backend.model.ProductInShoppingCart;
import com.s1141775.iprwc_g2_backend.service.ProductInShoppingCartService;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductInShoppingCartController {
    private final ProductInShoppingCartService productInShoppingCartService;

    public ProductInShoppingCartController(ProductInShoppingCartService productInShoppingCartService) {
        this.productInShoppingCartService = productInShoppingCartService;
    }

    public void saveProducts(BackendOrder backendOrder, FrontendOrder frontendOrder){
        for (ProductInShoppingCart product : frontendOrder.getProducts()){
            ProductInShoppingCart productInShoppingCart = new ProductInShoppingCart(product.getId(), backendOrder, product.getProduct(), product.getQuantity());
            this.productInShoppingCartService.save(productInShoppingCart);
        }
    }

    public ProductInShoppingCart[] getProductsByOrderId(String orderId) {
        List<ProductInShoppingCart> productsInShoppingCart = this.productInShoppingCartService.findByOrderId(orderId);

        return productsInShoppingCart.toArray(new ProductInShoppingCart[0]);
    }
}

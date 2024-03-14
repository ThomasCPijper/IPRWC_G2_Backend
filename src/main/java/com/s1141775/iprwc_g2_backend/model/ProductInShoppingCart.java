package com.s1141775.iprwc_g2_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_order")
public class ProductInShoppingCart {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_")
    private BackendOrder order;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private int quantity;

    public ProductInShoppingCart(String id, BackendOrder order, Product product, int numberOfProducts) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = numberOfProducts;
    }

    public ProductInShoppingCart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BackendOrder getOrder() {
        return order;
    }

    public void setOrder(BackendOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int numberOfProducts) {
        this.quantity = numberOfProducts;
    }


}

package com.s1141775.iprwc_g2_backend.model;

import jakarta.persistence.*;
import java.util.List;

public class FrontendOrder {


    private String name;
    private String customer;

    private ProductInShoppingCart[] products;

    private String email;

    private String address;

    public FrontendOrder(String name, String customer, ProductInShoppingCart[] products, String email, String address) {
        this.name = name;
        this.customer = customer;
        this.products = products;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ProductInShoppingCart[] getProducts() {
        return products;
    }

    public void setProducts(ProductInShoppingCart[] products) {
        this.products = products;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
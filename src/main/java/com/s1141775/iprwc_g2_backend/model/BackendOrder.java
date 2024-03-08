package com.s1141775.iprwc_g2_backend.model;

import jakarta.persistence.*;

@Entity
public class BackendOrder {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private String name;
    private String customer;

    private String email;

    private String address;

    public BackendOrder() {
        // Default constructor required by JPA
    }

    public BackendOrder(String name, String customer, String email, String address) {
        this.name = name;
        this.customer = customer;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
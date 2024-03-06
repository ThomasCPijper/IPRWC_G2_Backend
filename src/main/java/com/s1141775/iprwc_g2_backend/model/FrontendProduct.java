package com.s1141775.iprwc_g2_backend.model;

public class FrontendProduct {
    private String name;
    private String image;
    private double cost;

    public FrontendProduct(String name, String image, double cost) {
        this.name = name;
        this.image = image;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

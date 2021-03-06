package com.example.ServerlessKitchen;

import javax.persistence.*;


@Embeddable
public class Ingredient {

    private String name;
    private Integer quantity;

    public Ingredient() {
    }

    public Ingredient(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

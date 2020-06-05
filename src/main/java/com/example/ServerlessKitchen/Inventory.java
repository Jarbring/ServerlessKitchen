package com.example.ServerlessKitchen;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventory {

    @Id
    private String name;
    private Integer quantity;

    public Inventory() {

    }

    public Inventory(String name, Integer quantity) {
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

package com.example.ServerlessKitchen;

import java.util.List;

public class CountResponseRecipeOnly {

    Integer id;
    Integer count;

    public CountResponseRecipeOnly() {
    }

    public CountResponseRecipeOnly(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

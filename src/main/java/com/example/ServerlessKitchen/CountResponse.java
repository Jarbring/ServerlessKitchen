package com.example.ServerlessKitchen;

import java.util.List;

public class CountResponse {

    List<CountResponseRecipeOnly> recipes;
    Integer recipeCount;
    Integer unusedInventoryCount;

    public CountResponse() {

    }

    public CountResponse(List<CountResponseRecipeOnly> recipes, Integer recipeCount, Integer unusedInventoryCount) {
        this.recipes = recipes;
        this.recipeCount = recipeCount;
        this.unusedInventoryCount = unusedInventoryCount;
    }

    public List<CountResponseRecipeOnly> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<CountResponseRecipeOnly> recipes) {
        this.recipes = recipes;
    }

    public Integer getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(Integer recipeCount) {
        this.recipeCount = recipeCount;
    }

    public Integer getUnusedInventoryCount() {
        return unusedInventoryCount;
    }

    public void setUnusedInventoryCount(Integer unusedInventoryCount) {
        this.unusedInventoryCount = unusedInventoryCount;
    }
}

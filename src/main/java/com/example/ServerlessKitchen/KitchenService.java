package com.example.ServerlessKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitchenService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void clearDatabase() {
        recipeRepository.deleteAll();
        inventoryRepository.deleteAll();
    }

    public List<Recipe> getRecipes() {
        return (List<Recipe>)recipeRepository.findAll();
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }
}

package com.example.ServerlessKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Recipe modifyRecipe(Recipe update, Integer id) {

        Recipe updatedRecipe = getRecipeById(id);

            if (update.getName() != null) {
                updatedRecipe.setName(update.getName());
            }
            if (update.getIngredients() != null) {
                updatedRecipe.setIngredients(update.getIngredients());
            }
            if (update.getInstructions() != null) {
                updatedRecipe.setInstructions(update.getInstructions());
            }

        return recipeRepository.save(updatedRecipe);
    }

    public void make(Integer id) {

    }

    public List<Inventory> getInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    public void fillInventory(List<Inventory> inventoryList) {
        List<Inventory> inventory = getInventory();

        List<Inventory> newIngredients = inventoryList.stream()
                .filter(ingredient -> ingredient.getQuantity() > 0)
                .collect(Collectors.toList());

        newIngredients.stream().
                forEach(newIngredientItem -> {inventory.stream()
                .filter(inventoryItem -> {return inventoryItem.getName().equals(newIngredientItem.getName());})
                .limit(1)
                .forEach(inventoryItem -> {newIngredientItem.setQuantity(inventoryItem.getQuantity() + newIngredientItem.getQuantity());});
                ;});

        inventoryRepository.saveAll(newIngredients);
    }


    public boolean checkIfCanBeCooked(Integer id) {

        List<Inventory> inventory = getInventory();
        Recipe recipe = getRecipeById(id);

        List<Ingredient> recipeIngredientList = recipe.getIngredients().stream()
                .filter(recipeIngredient -> inventory.stream()
                        .anyMatch(inventoryIngredient -> inventoryIngredient.getName().equals(recipeIngredient.getName())
                        && recipeIngredient.getQuantity() <= inventoryIngredient.getQuantity()))
                .collect(Collectors.toList());


        return recipe.getIngredients().size() == recipeIngredientList.size();
    }

}

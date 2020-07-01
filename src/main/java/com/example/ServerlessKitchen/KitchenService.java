package com.example.ServerlessKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        List<Inventory> newInventory = getInventory();
        Recipe recipe = getRecipeById(id);

        newInventory.stream().
                forEach(newInventoryItem -> {recipe.getIngredients().stream()
                        .filter(ingredientItem -> {return ingredientItem.getName().equals(newInventoryItem.getName());})
                        .limit(1)
                        .forEach(ingredientItem -> {newInventoryItem.setQuantity(newInventoryItem.getQuantity() - ingredientItem.getQuantity());});
                    ;});

        inventoryRepository.saveAll(newInventory);
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findByQuantityGreaterThan(0);
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

    public boolean isRecipeQuantityBelowOne(Recipe recipe) {
        List<Ingredient> ingredientList = recipe.getIngredients();
        return ingredientList.stream()
                .anyMatch(ingredientItem -> ingredientItem.getQuantity() == null || ingredientItem.getQuantity() < 1);
    }

    public boolean isRecipeNameTaken(Recipe recipe) {
        List<Recipe> recipeList = getRecipes();
        return recipeList.stream()
                .anyMatch(recipeItem -> recipeItem.getName().equals(recipe.getName()));
    }


    public List<CountResponseRecipeOnly> countByRecipe() {

            List<Recipe> recipes = getRecipes();
            List<CountResponseRecipeOnly> countResponseRecipeOnyList = new ArrayList<>();
            List<Inventory> inventoryList = getInventory();

            for (int i = 0; i < recipes.size(); i++) {
                int min = Integer.MAX_VALUE;
                int found = 0;
                for (int j = 0; j < recipes.get(i).getIngredients().size(); j++) {

                    for (int k = 0; k < inventoryList.size(); k++) {
                        if (recipes.get(i).getIngredients().get(j).getName().equals(inventoryList.get(k).getName())) {
                            int count = inventoryList.get(k).getQuantity() / recipes.get(i).getIngredients().get(j).getQuantity();
                            found++;
                            if (count < min) {
                                min = count;
                            }
                            break;
                        }
                    }
                }
                countResponseRecipeOnyList.add(new CountResponseRecipeOnly(recipes.get(i).getId(), found == recipes.get(i).getIngredients().size() ? min : 0));

            }
            return countResponseRecipeOnyList;
    }

    public int optimizeTotalCount(Recipe r1) {

        return 11;
    }
}

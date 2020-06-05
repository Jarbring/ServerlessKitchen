package com.example.ServerlessKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KitchenRestController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/ping")
    public String ping() {

            Recipe recipe = new Recipe();
            Ingredient ingredient = new Ingredient("kött", 3);
            Ingredient ingredient2 = new Ingredient("fisk", 2);

            List<Ingredient> paket = new ArrayList<>();

            paket.add(ingredient);
            paket.add(ingredient2);


            recipe.setName("Mat");
            recipe.setInstructions("Skaka");
            recipe.setIngredients(paket);

            recipeRepository.save(recipe);

            inventoryRepository.save(new Inventory("ägg", 2));
            inventoryRepository.save(new Inventory("mjölk", 3));


        return "pong";
    }

    @PostMapping("/clear")
    public String clear() {

        return "DB is cleared";
    }

    @GetMapping("/recipes")
    public List<Recipe> recipes() {
        List<Recipe> recipes = new ArrayList<>();

        return recipes;
    }

    @PostMapping("/recipes/create")
    private Recipe createRecipe(@RequestBody Recipe recipe) {

        return recipe; //Just nu fel då den inte har ID
    }

    @GetMapping("/recipes/{id}")
    private Recipe getRecipe(@PathVariable Integer id) {
        Recipe recipe = new Recipe();

        return recipe; //return empty recipe atm
    }

    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable Integer id) {

        return "Your recipe is deleted";
    }

    @PatchMapping("recipes/{id}")
    private Recipe modifyRecipe(@PathVariable Integer id, Recipe recipe) {
        Recipe newRecipe = recipe;

        return newRecipe;
    }

    @PostMapping("/recipes/{id}/make")
    private String make(@PathVariable Integer id, @RequestBody Recipe recipe) {

        return "Yummy!";
    }

    @GetMapping("/inventory")
    private List<Ingredient> inventory() {
        List<Ingredient> inventory = new ArrayList<>();

        return inventory;
    }

    @PostMapping("/inventory/fill")
    private String fillInventory(@RequestBody List<Ingredient> ingredients) {

        return "You have refilled your inventory";
    }

    //"ADVANCED STUFF"-part

    @GetMapping("/recipes/get-count-by-recipe")
    private String getCountByRecipe() {
        return "get count by recipe(not finished)";
    }

    @GetMapping("/recipes/optimize-total-count")
    private String optimizeTotalCount() {
        return "optimize total count(not finished)";
    }

    @GetMapping("/recipes/optimize-total-waste")
    private String optimizeTotalWaste() {
        return "optimize total waste(not finished)";
    }

}




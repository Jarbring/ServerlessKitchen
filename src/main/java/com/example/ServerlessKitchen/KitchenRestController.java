package com.example.ServerlessKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class KitchenRestController {

    @Autowired
    private KitchenService kitchenService;


    //Autowire below are for testing purpose and shall be deleted.
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/ping")
    public String ping() {

        // Content below are for testing purpose and shall be deleted.
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
    public void clear(HttpServletResponse response) {
        kitchenService.clearDatabase();
        response.setStatus(204);
    }

    @GetMapping("/recipes")
    public List<Recipe> recipes() {
        return kitchenService.getRecipes();
    }

    @PostMapping("/recipes/create")
    private Recipe createRecipe(@RequestBody Recipe recipe, HttpServletResponse response) throws IOException {
        if(kitchenService.isRecipeQuantityBelowOne(recipe)) {
            response.sendError(400, "Ingredient quantity is less than 1");
            return null;
        }else if(kitchenService.isRecipeNameTaken(recipe)) {
            response.sendError(400, "Recipe name already exist");
            return null;
        }
        return kitchenService.createRecipe(recipe);
    }

    @GetMapping("/recipes/{id}")
    private Recipe getRecipe(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        if(kitchenService.getRecipeById(id) == null) {
            response.sendError(404, "Recipe does not exist");
            return null;
        }

        return kitchenService.getRecipeById(id);
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Integer id) {
        kitchenService.deleteRecipe(id);
    }

    @PatchMapping("recipes/{id}")
    private Recipe modifyRecipe(@PathVariable Integer id, @RequestBody Recipe recipe, HttpServletResponse response) throws IOException {
        if(recipe.getId() != null) {
            response.sendError(403, "You are not allowed to change the ID for this recipe");
            return null;
        }else if(recipe.getIngredients() != null && kitchenService.isRecipeQuantityBelowOne(recipe)) {
            response.sendError(403, "Ingredient quantity is less than 1");
            return null;
        }else if(recipe.getName() != null && kitchenService.isRecipeNameTaken(recipe)) {
            response.sendError(403, "Recipe name already exist");
            return null;
        }else if(kitchenService.getRecipeById(id) == null) {
            response.sendError(404, "Recipe ID does not exist");
            return null;
        }

        response.setStatus(200);
        return kitchenService.modifyRecipe(recipe, id);
    }

    @PostMapping("/recipes/{id}/make")
    private String make(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        if(kitchenService.getRecipeById(id) == null) {
            response.sendError(404, "Recipe ID does not exist");
            return null;
        }else if(!kitchenService.checkIfCanBeCooked(id)) {
            response.sendError(400, "Not enough ingredients");
            return null;
        }

        kitchenService.make(id);

        return "Yummy!";
    }

    @GetMapping("/inventory")
    private List<Inventory> getInventory() {
        return kitchenService.getInventory();
    }

    @PostMapping("/inventory/fill")
    private void fillInventory(@RequestBody List<Inventory> inventoryList, HttpServletResponse response) {
        if(kitchenService.isIngredientsAboveZero(inventoryList)) {
            kitchenService.fillInventory(inventoryList);
            response.setStatus(204);
        } else
            response.setStatus(400);


    }

    //"ADVANCED STUFF"-part

    @GetMapping("/recipes/get-count-by-recipe")
    private List<CountResponseRecipeOnly> getCountByRecipe() {
        return kitchenService.countByRecipe();
    }

    @GetMapping("/recipes/optimize-total-count")
    private CountResponse optimizeTotalCount() {
        return kitchenService.optimizeTotalCount();
    }

    @GetMapping("/recipes/optimize-total-waste")
    private String optimizeTotalWaste() {
        return "optimize total waste(not finished)";
    }

}




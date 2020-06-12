package com.example.ServerlessKitchen;

public class MethodsToTest {
    public static Recipe countByRecipe(Recipe recipe) {

        Recipe returnRecipe = recipe;

        returnRecipe.setCount(11);

        return returnRecipe;
    }

    public static int optimizeTotalCount(Recipe r1) {

        return 11;
    }
}

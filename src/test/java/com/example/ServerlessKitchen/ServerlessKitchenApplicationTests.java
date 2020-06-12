package com.example.ServerlessKitchen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ServerlessKitchenApplicationTests {

	Recipe r1;
	Recipe r2;
	Recipe r3;

	@Before
	public void createTestInfo() {
		List<Ingredient> sugarCakeIngredients = new ArrayList<>();
		List<Ingredient> cinnamonBunIngredients = new ArrayList<>();
		List<Ingredient> cottonCandyIngredients = new ArrayList<>();

		sugarCakeIngredients.add(new Ingredient("Sugar", 4));
		sugarCakeIngredients.add(new Ingredient("Cake mix", 1));
		cinnamonBunIngredients.add(new Ingredient("Cinnamon", 1));
		cinnamonBunIngredients.add(new Ingredient("Bun", 1));
		cottonCandyIngredients.add(new Ingredient("Sugar", 5));
		cottonCandyIngredients.add(new Ingredient("Pink", 10));

		r1 = new Recipe("Sugar cake", "Mix, then bake in oven", sugarCakeIngredients);
		r2 = new Recipe("Cinnamon Bun", "Put cinnamon on the bun", cinnamonBunIngredients);
		r3 = new Recipe("Cotton Candy", "Spinn it well!", cottonCandyIngredients);
		r1.setId(1);
		r2.setId(2);
		r3.setId(3);
	}

	@Test
	public void testTheTest() throws Exception {
		int result = 3;
		Assert.assertEquals("test-test", 3, result);
	}

	@Test
	public void testGetCountByRecipe() throws Exception {
		Assert.assertEquals("Sugar Cake ID", (Integer)1, MethodsToTest.countByRecipe(r1).getId());
		Assert.assertEquals("Sugar Cake Count", (Integer)11, MethodsToTest.countByRecipe(r1).getCount());
		Assert.assertEquals("Cinnamon Bun ID", (Integer)2, MethodsToTest.countByRecipe(r2).getId());
		Assert.assertEquals("Cinnamon Bun Count", (Integer)4, MethodsToTest.countByRecipe(r2).getCount());
		Assert.assertEquals("Cotton Candy ID", (Integer)3, MethodsToTest.countByRecipe(r3).getId());
		Assert.assertEquals("Cotton Candy Count", (Integer)1, MethodsToTest.countByRecipe(r3).getCount());
	}

	@Test
	public void testOptimizeTotalCount() throws Exception {
		Assert.assertEquals("Sugar Cake", 11, MethodsToTest.optimizeTotalCount(r1));
		Assert.assertEquals("Sugar Cake", 4, MethodsToTest.optimizeTotalCount(r2));
		Assert.assertEquals("Sugar Cake", 1, MethodsToTest.optimizeTotalCount(r3));
	}

}

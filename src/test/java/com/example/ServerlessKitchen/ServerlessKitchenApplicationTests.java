package com.example.ServerlessKitchen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServerlessKitchenApplicationTests {

	Recipe r1;
	Recipe r2;
	Recipe r3;

	@Autowired
	KitchenService kitchenService;

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
		cottonCandyIngredients.add(new Ingredient("Pink Color", 10));

		r1 = new Recipe("Sugar cake", "Mix, then bake in oven", sugarCakeIngredients);
		r2 = new Recipe("Cinnamon Bun", "Put cinnamon on the bun", cinnamonBunIngredients);
		r3 = new Recipe("Cotton Candy", "Spinn it well!", cottonCandyIngredients);
		r1.setId(1);
		r2.setId(2);
		r3.setId(3);
		kitchenService.createRecipe(r1);
		kitchenService.createRecipe(r2);
		kitchenService.createRecipe(r3);

		List<Inventory> inventoryList = new ArrayList<>();
		inventoryList.add(new Inventory("Sugar", 44));
		inventoryList.add(new Inventory("Cake mix", 11));
		inventoryList.add(new Inventory("Cinnamon", 5));
		inventoryList.add(new Inventory("Bun", 4));
		inventoryList.add(new Inventory("Pink Color", 10));
		kitchenService.fillInventory(inventoryList);

	}

	/*@Test
	public void testTheTest() throws Exception {
		int result = 3;
		Assert.assertEquals("test-test", 3, result);
	}*/

	@Test
	public void testGetCountByRecipe() throws Exception {
		Assert.assertEquals("Sugar Cake ID", (Integer)1, kitchenService.countByRecipe().get(0).getId());
		Assert.assertEquals("Sugar Cake Count", (Integer)11, kitchenService.countByRecipe().get(0).getCount());
		Assert.assertEquals("Cinnamon Bun ID", (Integer)2, kitchenService.countByRecipe().get(1).getId());
		Assert.assertEquals("Cinnamon Bun Count", (Integer)4, kitchenService.countByRecipe().get(1).getCount());
		Assert.assertEquals("Cotton Candy ID", (Integer)3, kitchenService.countByRecipe().get(2).getId());
		Assert.assertEquals("Cotton Candy Count", (Integer)1, kitchenService.countByRecipe().get(2).getCount());
	}

	@Test
	public void testOptimizeTotalCount() throws Exception {
		Assert.assertEquals("Sugar Cake", (Integer)15, kitchenService.optimizeTotalCount().getRecipeCount());
		Assert.assertEquals("Sugar Cake", (Integer)11, kitchenService.optimizeTotalCount().getUnusedInventoryCount());
	}

}

package net.CyanWool.api.inventory.recipes;

import java.util.List;

import net.CyanWool.api.inventory.ItemType;

public interface RecipeManager {

    public boolean register(Recipe recipe);

    public boolean registerAll(List<Recipe> recipes);

    public boolean remove(Recipe recipe);

    public void clear();

    public List<Recipe> getAllRecipes();

    public List<ShapedRecipe> getShapedRecipes();

    public List<ShapelessRecipe> getShapelessRecipes();

    public List<FurnaceRecipe> getFurnaceRecipes();

    public ShapedRecipe matchShapedRecipe(List<ItemType> itemType);

    public ShapelessRecipe matchShapelessRecipe(List<ItemType> itemType);

    public boolean registerFuel(FurnaceFuel fuel);

    public boolean remove(FurnaceFuel fuel);

    public List<FurnaceFuel> getAllFuels();
}
package net.CyanWool.api.inventory.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.CyanWool.api.inventory.ItemType;

public class SimpleRecipeManager implements RecipeManager {

    private List<ShapedRecipe> shapedRecipes = new ArrayList<ShapedRecipe>();
    private List<ShapelessRecipe> shapelessRecipes = new ArrayList<ShapelessRecipe>();
    private List<FurnaceRecipe> furnaceRecipes = new ArrayList<FurnaceRecipe>();
    private List<FurnaceFuel> fuels = new ArrayList<FurnaceFuel>();

    @Override
    public boolean register(Recipe recipe) {
        if (recipe instanceof ShapedRecipe) {
            shapedRecipes.add((ShapedRecipe) recipe);
            return true;
        } else if (recipe instanceof ShapelessRecipe) {
            shapelessRecipes.add((ShapelessRecipe) recipe);
            return true;
        } else if (recipe instanceof FurnaceRecipe) {
            furnaceRecipes.add((FurnaceRecipe) recipe);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerAll(List<Recipe> recipes) {
        boolean failed = false;
        for (Recipe recipe : recipes) {
            failed |= register(recipe);
        }
        return failed;
    }

    @Override
    public boolean remove(Recipe recipe) {
        if (recipe instanceof ShapedRecipe) {
            Iterator<ShapedRecipe> it = shapedRecipes.iterator();
            while (it.hasNext()) {
                ShapedRecipe i = it.next();
                if (i.equals(recipe)) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        shapedRecipes.clear();
        shapelessRecipes.clear();
        furnaceRecipes.clear();
        fuels.clear();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.addAll(furnaceRecipes);
        recipes.addAll(shapedRecipes);
        recipes.addAll(shapelessRecipes);
        return recipes;
    }

    @Override
    public List<ShapedRecipe> getShapedRecipes() {
        return shapedRecipes;
    }

    @Override
    public List<ShapelessRecipe> getShapelessRecipes() {
        return shapelessRecipes;
    }

    @Override
    public ShapedRecipe matchShapedRecipe(List<ItemType> itemType) {
        for (ShapedRecipe recipe : getShapedRecipes()) {
            if (recipe.getItems().values().containsAll(itemType)) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public ShapelessRecipe matchShapelessRecipe(List<ItemType> itemType) {
        for (ShapelessRecipe recipe : getShapelessRecipes()) {
            if (recipe.getItems().containsAll(itemType)) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public boolean registerFuel(FurnaceFuel fuel) {
        fuels.add(fuel);
        return true;
    }

    @Override
    public boolean remove(FurnaceFuel fuel) {
        Iterator<FurnaceFuel> it = fuels.iterator();
        while (it.hasNext()) {
            FurnaceFuel i = it.next();
            i.equals(fuel);
            it.remove();
            return true;
        }
        return false;
    }

    @Override
    public List<FurnaceFuel> getAllFuels() {
        return fuels;
    }

    @Override
    public List<FurnaceRecipe> getFurnaceRecipes() {
        return furnaceRecipes;
    }

}
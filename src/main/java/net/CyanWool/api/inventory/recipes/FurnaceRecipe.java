package net.CyanWool.api.inventory.recipes;

import net.CyanWool.api.inventory.ItemStack;

public class FurnaceRecipe implements Recipe {

    private ItemStack result;
    private ItemStack source;

    public FurnaceRecipe(ItemStack item, ItemStack source) {
        this.result = item;
        this.source = source;
    }

    @Override
    public ItemStack getResult() {
        return result;
    }

    /**
     * @return the source
     */
    public ItemStack getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(ItemStack source) {
        this.source = source;
    }

}
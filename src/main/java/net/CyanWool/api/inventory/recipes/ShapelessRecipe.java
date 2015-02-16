package net.CyanWool.api.inventory.recipes;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.ItemType;

public class ShapelessRecipe implements Recipe {

    private List<ItemType> items;
    private ItemStack result;

    public ShapelessRecipe(ItemStack item) {
        this.items = new ArrayList<ItemType>();
        this.result = item;
    }

    public void addIndegrient(ItemStack item) {
        addIndegrient(item.getItemType());
    }

    public void addIndegrient(ItemType type) {
        items.add(type);
    }

    public List<ItemType> getItems() {
        return items;
    }

    @Override
    public ItemStack getResult() {
        return result;
    }

}
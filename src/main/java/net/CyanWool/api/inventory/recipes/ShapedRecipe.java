package net.CyanWool.api.inventory.recipes;

import java.util.HashMap;
import java.util.Map;

import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.ItemType;

public class ShapedRecipe implements Recipe {

    private Map<Integer, ItemType> items;
    private ItemStack result;

    public ShapedRecipe(ItemStack item) {
        this.items = new HashMap<Integer, ItemType>();
        this.result = item;
    }

    public void addIndegrient(int slot, ItemStack item) {
        addIndegrient(slot, item.getItemType());
    }

    public void addIndegrient(int slot, ItemType type) {
        items.put(slot, type);
    }

    public Map<Integer, ItemType> getItems() {
        return items;
    }

    @Override
    public ItemStack getResult() {
        return result;
    }

}
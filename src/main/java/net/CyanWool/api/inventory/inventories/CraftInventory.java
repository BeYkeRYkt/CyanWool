package net.CyanWool.api.inventory.inventories;

import java.util.List;

import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;

public interface CraftInventory extends Inventory {

    public ItemStack getResult();

    public List<ItemStack> getMatrix();

    public void setResult(ItemStack item);
}
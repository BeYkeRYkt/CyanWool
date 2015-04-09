package net.CyanWool.api.inventory.inventories;

import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;

public interface FurnaceInventory extends Inventory {

    public ItemStack getResult();

    public void setResult(ItemStack item);

    public ItemStack getFuel();

    public void setFuel(ItemStack item);

    public ItemStack getGoal();

    public void setGoal(ItemStack item);
}
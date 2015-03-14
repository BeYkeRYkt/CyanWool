package net.CyanWool.api.inventory.inventories;

import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;

public interface EntityInventory extends Inventory {

    public ItemStack[] getArmorContents();

    public ItemStack getHelmet();

    public ItemStack getChestplate();

    public ItemStack getLeggings();

    public ItemStack getBoots();

    public void setArmorContents(ItemStack[] items);

    public void setHelmet(ItemStack helmet);

    public void setChestplate(ItemStack chestplate);

    public void setLeggings(ItemStack leggings);

    public void setBoots(ItemStack boots);

    public ItemStack getItemInHand();

    public void setItemInHand(ItemStack stack);
}
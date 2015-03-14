package net.CyanWool.inventory.inventories;

import net.CyanWool.api.inventory.InventoryType;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.inventories.EntityInventory;
import net.CyanWool.inventory.CyanInventory;

public class CyanEntityInventory extends CyanInventory implements EntityInventory {

    private ItemStack[] armor;

    public CyanEntityInventory(String name) {
        super(5, name, InventoryType.CUSTOM);
        this.armor = new ItemStack[5];
    }

    @Override
    public ItemStack[] getArmorContents() {
        return armor;
    }

    @Override
    public ItemStack getHelmet() {
        return armor[1];
    }

    @Override
    public ItemStack getChestplate() {
        return armor[2];
    }

    @Override
    public ItemStack getLeggings() {
        return armor[3];
    }

    @Override
    public ItemStack getBoots() {
        return armor[4];
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        this.armor = items;
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        this.armor[1] = helmet;
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        this.armor[2] = chestplate;
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        this.armor[3] = leggings;
    }

    @Override
    public void setBoots(ItemStack boots) {
        this.armor[4] = boots;
    }

    @Override
    public ItemStack getItemInHand() {
        return armor[0];
    }

    @Override
    public void setItemInHand(ItemStack stack) {
        this.armor[0] = stack;
    }
}
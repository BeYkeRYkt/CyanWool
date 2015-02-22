package net.CyanWool.api.entity.objects;

import net.CyanWool.api.inventory.ItemStack;

public interface Item extends Projectile {

    public ItemStack getItemStack();

    public void setItemStack(ItemStack item);

    public boolean isPickup();

    public void setPickup(boolean flag);

    public boolean isDropedByPlayer();
}
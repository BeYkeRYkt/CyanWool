package net.CyanWool.api.inventory;

import java.util.List;

import net.CyanWool.api.entity.player.Human;

public interface Inventory {

    public int getSize();

    public ItemStack getItemStack(int slot);

    public void setItemStack(int slot, ItemStack item);

    public String getName();

    public boolean hasCustomName();

    public boolean isUseableByPlayer(Human player);

    public void addItems(ItemStack... items);

    public ItemStack[] getContents();

    public void setContents(ItemStack[] items);

    public boolean contains(int materialId);

    public boolean contains(ItemStack item);

    public void clear();

    public List<Human> getViewers();

    public InventoryType getType();

    public void closeInventory(Human cyanHuman);

    public void openInventory(Human cyanHuman);
}
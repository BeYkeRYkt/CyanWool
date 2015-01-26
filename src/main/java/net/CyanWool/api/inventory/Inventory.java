package net.CyanWool.api.inventory;

import java.util.List;

import net.CyanWool.api.entity.player.Human;

public interface Inventory {

    public int getSize();

    public ItemStack getItemStack(int slot);

    public void setItemStack(int slot, ItemStack item);

    public String getName();

    public boolean hasCustomName();

    public int getStackLimit();

    public boolean isUseableByPlayer(Human player);

    public void openInventory(Human player);

    public void closeInventory(Human player);
    
    public int getMaxStackSize();
    
    public void setMaxStackSize(int size);
    
    public void addItems(ItemStack... items);
    
    public ItemStack[] getContents();
    
    public void setContents(ItemStack[] items);
    
    public boolean contains(int materialId);
    
    public boolean contains(ItemStack item);
    
    public void clear();
    
    public List<Human> getViewers();
    
    public InventoryType getType();
}
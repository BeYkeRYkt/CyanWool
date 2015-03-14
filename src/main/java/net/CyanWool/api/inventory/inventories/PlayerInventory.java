package net.CyanWool.api.inventory.inventories;

public interface PlayerInventory extends EntityInventory {

    public int getHeldItemSlot();

    public void setHeldItemSlot(int slot);

}
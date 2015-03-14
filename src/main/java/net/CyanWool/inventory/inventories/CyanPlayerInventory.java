package net.CyanWool.inventory.inventories;

import net.CyanWool.api.inventory.inventories.PlayerInventory;

public class CyanPlayerInventory extends CyanEntityInventory implements PlayerInventory {

    private int slot;

    public CyanPlayerInventory(String name) {
        super(name);
    }

    @Override
    public int getHeldItemSlot() {
        return slot;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        this.slot = slot;
    }

}
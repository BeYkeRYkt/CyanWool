package net.CyanWool.api.block.states;

import net.CyanWool.api.block.BlockState;
import net.CyanWool.api.inventory.Inventory;

public interface ChestState extends BlockState {

    public Inventory getInventory();

    public void setOpen(boolean open);

    public boolean isOpen();
}
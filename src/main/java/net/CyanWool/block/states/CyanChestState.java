package net.CyanWool.block.states;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.entity.TileChest;
import net.CyanWool.api.block.states.ChestState;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.block.CyanBlockState;

public class CyanChestState extends CyanBlockState implements ChestState {

    private boolean open;

    public CyanChestState(Block block) {
        super(block);
    }

    private TileChest getTileEntity() {
        return (TileChest) getBlock().getTileEntity();
    }

    @Override
    public Inventory getInventory() {
        return getTileEntity().getInventory();
    }

    @Override
    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

}
package net.CyanWool.block.entity;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.entity.TileContainer;
import net.CyanWool.api.inventory.Inventory;

public abstract class CyanTileContainer extends CyanTileEntity implements TileContainer {

    private Inventory inv;

    public CyanTileContainer(Block block, Inventory inv) {
        super(block);
        this.inv = inv;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
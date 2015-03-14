package net.CyanWool.api.block.entity;

import net.CyanWool.api.inventory.Inventory;

public interface TileContainer extends TileEntity {

    public Inventory getInventory();

}
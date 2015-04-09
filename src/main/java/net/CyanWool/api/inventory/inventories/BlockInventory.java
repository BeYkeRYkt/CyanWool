package net.CyanWool.api.inventory.inventories;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.inventory.Inventory;

public interface BlockInventory extends Inventory {

    public Block getBlock();

}
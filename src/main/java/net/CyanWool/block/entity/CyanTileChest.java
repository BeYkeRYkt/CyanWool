package net.CyanWool.block.entity;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.entity.TileChest;
import net.CyanWool.api.block.states.ChestState;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.InventoryType;
import net.CyanWool.inventory.CyanInventory;

public class CyanTileChest extends CyanTileContainer implements TileChest {

    public CyanTileChest(Block block) {
        super(block, new CyanInventory(36, "Chest", InventoryType.CHEST));// TODO
    }

    @Override
    public Inventory getInventory() {
        return ((ChestState) getBlockState()).getInventory();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
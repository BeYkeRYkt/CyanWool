package net.CyanWool.block.entity;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockState;
import net.CyanWool.api.block.entity.TileEntity;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public abstract class CyanTileEntity implements TileEntity {

    private Block block;

    public CyanTileEntity(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public CompoundTag getCompoundTag() {
        return block;
    }

    @Override
    public BlockState getBlockState() {
        return block.getBlockState();
    }

}
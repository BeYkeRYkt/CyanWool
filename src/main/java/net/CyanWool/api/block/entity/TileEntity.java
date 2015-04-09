package net.CyanWool.api.block.entity;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockState;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public interface TileEntity {

    public Block getBlock();

    public void update();

    public CompoundTag getCompoundTag();

    // public void loadCompoundTag(CompoundTag tag);

    // public void saveCompoundTag(CompoundTag tag);

    public BlockState getBlockState();
}
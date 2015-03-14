package net.CyanWool.block;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockState;
import net.CyanWool.api.block.BlockType;

public class CyanBlockState implements BlockState {

    private Block block;

    public CyanBlockState(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public BlockType getBlockType() {
        return block.getBlockType();
    }

}
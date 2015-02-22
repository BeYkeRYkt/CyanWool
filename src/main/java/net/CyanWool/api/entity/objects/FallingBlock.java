package net.CyanWool.api.entity.objects;

import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;

public interface FallingBlock extends Entity {

    public BlockType getBlockType();

    public boolean isDropItem();

    public boolean setDropItem(boolean flag);

    public void transformToBlock();

}
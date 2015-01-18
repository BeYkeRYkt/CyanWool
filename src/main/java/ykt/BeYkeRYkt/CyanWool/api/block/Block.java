package ykt.BeYkeRYkt.CyanWool.api.block;

import ykt.BeYkeRYkt.CyanWool.api.world.Location;

public interface Block {

    public Location getLocation();

    public BlockType getBlockType();

    public void breakBlock();

    public int getLightValue();

    public Block getRelative(final int modX, final int modY, final int modZ);

    public Block getRelative(BlockSide side);
    // onBlockPlace ? No.

}
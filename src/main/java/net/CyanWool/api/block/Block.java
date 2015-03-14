package net.CyanWool.api.block;

import net.CyanWool.api.block.entity.TileEntity;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

public interface Block {

    public Location getLocation();

    public BlockType getBlockType();

    public void breakBlock();

    public int getLightValue();

    public int getLightFromSky();

    public Block getRelative(final int modX, final int modY, final int modZ);

    public Block getRelative(BlockSide side);

    public void transformToFallingBlock();

    public World getWorld();

    public int getX();

    public int getY();

    public int getZ();

    public Chunk getChunk();

    public void setBlock(int id);

    public void setBlock(BlockType type);

    public void setData(int data);

    public BlockState getBlockState();

    public TileEntity getTileEntity();

    // Maybe todo...
}
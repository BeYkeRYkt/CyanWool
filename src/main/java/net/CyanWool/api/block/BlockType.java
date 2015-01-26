package net.CyanWool.api.block;

import java.util.List;

import net.CyanWool.api.inventory.ItemStack;

public interface BlockType {

    public boolean isDrop();

    public void setDrop(boolean isDrop);

    public int getID();

    public int getData();

    public BlockSound getStepSound();

    public void setStepSound(BlockSound stepSound);

    public BlockSound getDigSound();

    public void setDigSound(BlockSound digSound);

    public BlockSound getBreakSound();

    public void setBreakSound(BlockSound breakSound);

    public BlockSound getPlaceSound();

    public void setPlaceSound(BlockSound placeSound);

    public List<ItemStack> getDrop();

    public BlockType cloneBlock();
}
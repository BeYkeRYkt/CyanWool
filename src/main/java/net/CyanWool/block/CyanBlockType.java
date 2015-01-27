package net.CyanWool.block;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.block.BlockSound;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

public class CyanBlockType implements BlockType {

    private int id;
    private int data;
    private boolean isDrop;

    private BlockSound stepSound;
    private BlockSound digSound;
    private BlockSound breakSound;
    private BlockSound placeSound;

    private List<ItemStack> drop;

    public CyanBlockType(int id, int data) {
        this.id = id;
        this.data = data;
        this.drop = new ArrayList<ItemStack>();
    }

    @Override
    public boolean isDrop() {
        return isDrop;
    }

    @Override
    public void setDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getData() {
        return data;
    }

    public void onBlockPlace(Location location) {
    }

    public void onBlockDestroy(Location location) {
    }

    public void onBlockLeftInteract(Player player) {
    }

    public void onBlockRightInteract(Player player) {
    }

    public void onEntityWalking(Entity entity) {
    }

    @Override
    public BlockSound getStepSound() {
        return stepSound;
    }

    @Override
    public void setStepSound(BlockSound stepSound) {
        this.stepSound = stepSound;
    }

    @Override
    public BlockSound getDigSound() {
        return this.digSound;
    }

    @Override
    public void setDigSound(BlockSound digSound) {
        this.digSound = digSound;
    }

    @Override
    public BlockSound getBreakSound() {
        return this.breakSound;
    }

    @Override
    public void setBreakSound(BlockSound breakSound) {
        this.breakSound = breakSound;
    }

    @Override
    public BlockSound getPlaceSound() {
        return this.placeSound;
    }

    @Override
    public void setPlaceSound(BlockSound placeSound) {
        this.placeSound = placeSound;
    }

    @Override
    public List<ItemStack> getDrop() {
        return drop;
    }

    @Override
    public BlockType cloneBlock() {
        CyanBlockType clone = new CyanBlockType(id, data);
        clone.setBreakSound(breakSound);
        clone.setDigSound(digSound);
        clone.setDrop(isDrop);
        clone.setPlaceSound(placeSound);
        clone.setStepSound(stepSound);
        clone.getDrop().addAll(drop);
        return clone;
    }
}
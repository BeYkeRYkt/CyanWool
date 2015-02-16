package net.CyanWool.api.block;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

public class BlockType { //??

    private int id;
    private int data;
    private boolean isDrop;

    private SoundInfo stepSound;
    private SoundInfo digSound;
    private SoundInfo breakSound;
    private SoundInfo placeSound;

    private List<ItemStack> drop;
    private int maxData;

    public BlockType(int id, int data, int maxData) {
        this.id = id;
        this.data = data;
        this.drop = new ArrayList<ItemStack>();
        this.maxData = data;
    }

    public boolean isDrop() {
        return isDrop;
    }

    public void setDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }

    public int getID() {
        return id;
    }

    public int getData() {
        return data;
    }
    
    public void setData(int data){
        this.data = data;
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

    public void onPlayerBreaking(Player player) {
    }

    public SoundInfo getStepSound() {
        return stepSound;
    }

    public void setStepSound(SoundInfo stepSound) {
        this.stepSound = stepSound;
    }

    public SoundInfo getDigSound() {
        return this.digSound;
    }

    public void setDigSound(SoundInfo digSound) {
        this.digSound = digSound;
    }

    public SoundInfo getBreakSound() {
        return this.breakSound;
    }

    public void setBreakSound(SoundInfo breakSound) {
        this.breakSound = breakSound;
    }

    public SoundInfo getPlaceSound() {
        return this.placeSound;
    }

    public void setPlaceSound(SoundInfo placeSound) {
        this.placeSound = placeSound;
    }

    public List<ItemStack> getDrop() {
        return drop;
    }
    
    public int getMaxData(){
        return maxData;
    }
}
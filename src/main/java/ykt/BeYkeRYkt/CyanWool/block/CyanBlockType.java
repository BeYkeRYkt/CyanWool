package ykt.BeYkeRYkt.CyanWool.block;

import java.util.ArrayList;
import java.util.List;

import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;

import ykt.BeYkeRYkt.CyanWool.api.block.BlockSound;
import ykt.BeYkeRYkt.CyanWool.api.block.BlockType;
import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;

public class CyanBlockType implements BlockType {

    private int id;
    private int data;
    private boolean isDrop;
    private String customData;

    private BlockSound stepSound;
    private BlockSound digSound;
    private BlockSound breakSound;
    private BlockSound placeSound;

    private List<ItemStack> drop;

    private ParticleEffect particle;

    public CyanBlockType(int id, int data) {
        this(id, data, null);
    }

    public CyanBlockType(int id, int data, String customData) {
        this.id = id;
        this.data = data;
        this.customData = customData;
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

    @Override
    public String getCustomID() {
        return customData;
    }

    @Override
    public boolean hasCustomID() {
        return getCustomID() != null;
    }

    @Override
    public void onBlockPlace(Location location) {
    }

    @Override
    public void onBlockDestroy(Location location) {
    }

    @Override
    public void onBlockLeftInteract(Player player) {
    }

    @Override
    public void onBlockRightInteract(Player player) {
    }

    @Override
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
    public BlockType clone() {
        CyanBlockType clone = new CyanBlockType(id, data, customData);
        clone.setBreakSound(breakSound);
        clone.setDigSound(digSound);
        clone.setDrop(isDrop);
        clone.setPlaceSound(placeSound);
        clone.setStepSound(stepSound);
        clone.setParticle(particle);
        clone.getDrop().addAll(drop);
        return clone;
    }

    @Override
    public ParticleEffect getBreakParticle() {
        return null;
    }

    @Override
    public void setParticle(ParticleEffect particle) {
        this.particle = particle;
    }
}
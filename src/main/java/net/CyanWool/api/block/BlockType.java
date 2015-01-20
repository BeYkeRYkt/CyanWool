package net.CyanWool.api.block;

import java.util.List;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;

public interface BlockType {

    public boolean isDrop();

    public void setDrop(boolean isDrop);

    public int getID();

    public int getData();

    public void onBlockPlace(Location location);

    public void onBlockDestroy(Location location);

    public void onBlockLeftInteract(Player player);

    public void onBlockRightInteract(Player player);

    public void onEntityWalking(Entity entity);

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

    public ParticleEffect getBreakParticle();

    public void setParticle(ParticleEffect particle);
}
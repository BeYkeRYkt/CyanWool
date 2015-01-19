package net.CyanWool.block;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockSide;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.values.world.effect.BreakBlockEffectData;

public class CyanBlock implements Block {

    private final Location location;
    private BlockType type;

    public CyanBlock(Location location, BlockType type) {
        this.location = location;
        this.type = type;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public BlockType getBlockType() {
        return type;
    }

    @Override
    public void breakBlock() {
        // TODO: setAir...

        // play break Effect
        getLocation().getWorld().playEffect(getLocation(), type.getBreakParticle(), new BreakBlockEffectData(getBlockType().getID()));// ?
        getLocation().getWorld().playSoundEffect(getLocation(), getBlockType().getBreakSound().getName(), getBlockType().getBreakSound().getVolume(), getBlockType().getBreakSound().getPitch());
        
        type.onBlockDestroy(getLocation());
        
        // apply physics...

        if (type.isDrop() && type.getDrop().isEmpty()) {
            for (ItemStack item : type.getDrop()) {
                getLocation().getWorld().dropItemStack(item);
            }
        }
    }

    @Override
    public int getLightValue() {
        return getLocation().getChunk().getBlockLight(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        Location newLocation = new Location(getLocation().getWorld(), getLocation().getBlockX() + modX, getLocation().getBlockY() + modY, getLocation().getBlockZ() + modZ);
        return getLocation().getWorld().getBlock(newLocation);
    }

    @Override
    public Block getRelative(BlockSide side) {
        return getRelative(side.getX(), side.getY(), side.getZ());
    }

}
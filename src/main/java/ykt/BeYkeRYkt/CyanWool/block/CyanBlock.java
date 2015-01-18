package ykt.BeYkeRYkt.CyanWool.block;

import org.spacehq.mc.protocol.data.game.values.world.effect.BreakBlockEffectData;

import ykt.BeYkeRYkt.CyanWool.api.block.Block;
import ykt.BeYkeRYkt.CyanWool.api.block.BlockSide;
import ykt.BeYkeRYkt.CyanWool.api.block.BlockType;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;

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
        // apply physics...

        if (type.isDrop() && type.getDrop().isEmpty()) {
            for (ItemStack item : type.getDrop()) {
                getLocation().getWorld().dropItemStack(item);
            }
        }
    }

    @Override
    public int getLightValue() {
        return 0;
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
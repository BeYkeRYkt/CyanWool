package net.CyanWool.block;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockSide;
import net.CyanWool.api.block.BlockState;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.block.entity.TileEntity;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

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
        // play break Effect
        // getLocation().getWorld().playEffect(getLocation(),
        // ParticleEffect.BREAK_BLOCK, new
        // BreakBlockEffectData(getBlockType().getID()));// ?

        if (getBlockType().getBreakSound().getSound() != GenericSound.CLICK) {
            getLocation().getWorld().playSound(getLocation(), getBlockType().getBreakSound().getSound(), getBlockType().getBreakSound().getVolume(), getBlockType().getBreakSound().getPitch());
        } else {
            getLocation().getWorld().playSound(getLocation(), getBlockType().getBreakSound().getName(), getBlockType().getBreakSound().getVolume(), getBlockType().getBreakSound().getPitch());
        }

        BlockType ctype = type;
        ctype.onBlockDestroy(getLocation());

        // apply physics...

        if (type.isDrop() && type.getDrop().isEmpty()) {
            for (ItemStack item : type.getDrop()) {
                getLocation().getWorld().dropItemStack(getLocation(), item);
            }
        }
        // TODO: setAir...
        getLocation().getChunk().setBlock(getLocation().getBlockX(), getLocation().getBlockY(), getLocation().getBlockZ(), 0);

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

    @Override
    public void transformToFallingBlock() {
        if (getRelative(0, -1, 0).getBlockType().getID() == 0) {
            getWorld().spawnFallingBlock(getLocation(), getBlockType());
        }
    }

    @Override
    public World getWorld() {
        return getLocation().getWorld();
    }

    @Override
    public int getLightFromSky() {
        return getChunk().getSkyLight(getX(), getY(), getZ());
    }

    @Override
    public int getX() {
        return location.getBlockX();
    }

    @Override
    public int getY() {
        return location.getBlockY();
    }

    @Override
    public int getZ() {
        return location.getBlockZ();
    }

    @Override
    public Chunk getChunk() {
        return location.getChunk();
    }

    @Override
    public void setBlock(int id) {
        getWorld().setBlock(getLocation(), id, 0);
    }

    @Override
    public void setBlock(BlockType type) {
        getWorld().setBlock(getLocation(), type);
    }

    @Override
    public void setData(int data) {
        getChunk().setData(location, data);
    }

    @Override
    public BlockState getBlockState() {
        TileEntity entity = getTileEntity();
        if (entity != null) {
            BlockState state = entity.getBlockState();
            if (state != null) {
                return state;
            }
        }
        return new CyanBlockState(this);
    }

    @Override
    public TileEntity getTileEntity() {
        return getWorld().getTileEntity(getLocation());
    }

}
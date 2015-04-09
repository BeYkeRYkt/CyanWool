package net.CyanWool.api.entity;

import net.CyanWool.api.Server;
import net.CyanWool.api.entity.component.ComponentManager;
import net.CyanWool.api.entity.meta.MetadataMap;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public interface Entity {

    public Location getLocation();

    public World getWorld();

    public void teleport(Location location);

    public boolean isAlive();

    public boolean isBurning();

    public boolean isImmuneToFire();

    public boolean isInvisible();

    public boolean isSneaking();

    public boolean isSprinting();

    public boolean isWet();

    public boolean onGround();

    public void kill();

    public void onTick();

    public void setInvisible(boolean flag);

    public int getFireTicks();

    public void setFireTicks(int ticks);

    public EntityType getEntityType();

    public int getEntityID();

    public void setEntityID(int id);

    public int getLivedTicks();

    public ComponentManager getComponentManager();

    public MetadataMap getMetadata();

    public boolean isMoveable();

    public void setMoveable(boolean flag);

    public Chunk getChunk();

    public void setOnGround(boolean onGround);

    // For movement component
    public void setSneaking(boolean flag);

    public void setSprinting(boolean flag);

    public void setPassenger(Entity entity);

    public Entity getPassenger();

    public Entity getVehicle();

    public void setVehicle(Entity entity);

    public boolean canSeeEntity(Entity entity);

    public boolean canSeeLocation(Location location);

    // public void loadCompoundTag(CompoundTag tag);

    // public void saveCompoundTag(CompoundTag tag);

    public CompoundTag getCompoundTag();

    public boolean isMonster();

    public Server getServer();

    public void loadData();

    public void saveData();
}
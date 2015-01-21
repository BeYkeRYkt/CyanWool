package net.CyanWool.api.entity;

import java.util.List;

import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.mc.protocol.data.game.values.entity.MobType;
import org.spacehq.packetlib.packet.Packet;

public interface Entity {

    public Location getLocation();

    public World getWorld();

    public void teleport(Location location);

    public int getEntityID();
    
    public void setEntityID(int id);

    public boolean isAlive();

    public boolean isBurning();

    public boolean isInWater();

    public float getEyeHeight();

    public boolean isEntityInvulnerable();

    public boolean isImmuneToFire();

    public boolean isInvisible();

    public boolean isRiding();

    public boolean isSneaking();

    public boolean isSprinting();

    public boolean isWet();

    public boolean isNoclip();

    public boolean onGround();

    public void kill();

    public void setInvisible(boolean flag);

    public void setSneaking(boolean flag);

    public void setSprinting(boolean flag);

    public void setPassenger(Entity entity);

    public Entity getPassenger();

    public Entity getVehicle();

    public void setVehicle(Entity entity);

    public void onTick();

    public int getFireTicks();

    public void setFireTicks(int ticks);

    public List<Packet> getUpdatePackets();

    public MobType getMobType();
    
    public List<Packet> getSpawnPackets();
}
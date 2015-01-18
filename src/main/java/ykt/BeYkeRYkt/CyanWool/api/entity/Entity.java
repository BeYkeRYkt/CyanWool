package ykt.BeYkeRYkt.CyanWool.api.entity;

import java.util.List;

import org.spacehq.packetlib.packet.Packet;

import ykt.BeYkeRYkt.CyanWool.api.world.Location;
import ykt.BeYkeRYkt.CyanWool.api.world.World;

public interface Entity {

    public Location getLocation();

    public World getWorld();

    public void teleport(Location location);

    public int getEntityID();

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
}
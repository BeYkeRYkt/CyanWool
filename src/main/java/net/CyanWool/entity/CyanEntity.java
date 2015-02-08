package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import org.spacehq.packetlib.packet.Packet;

public class CyanEntity implements Entity {

    private boolean moved;
    private boolean teleported;
    private boolean rotated;

    private Location location;
    private Location prevLoc;
    private boolean isAlive;

    private int fireTicks;
    private int entityId;
    private boolean invisible;
    private boolean sneak;
    private boolean sprint;
    private boolean onGround;
    private int livedTicks;
    
    private Entity passenger;
    private Entity vehicle;

    public CyanEntity(Location location) {
        this.prevLoc = location.clone();
        this.location = location.clone();
        //CyanWool.getEntityManager().register(this);
        // TODO
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public World getWorld() {
        return location.getWorld();
    }

    @Override
    public void teleport(Location location) {
        this.teleported = true;
        this.location = location;
    }

    @Override
    public int getRegisterID() {
        return entityId;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean isBurning() {
        return getFireTicks() > 0;
    }

    @Override
    public boolean isImmuneToFire() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isInvisible() {
        return invisible;
    }

    @Override
    public boolean isRiding() {
        return getVehicle() != null;
    }

    @Override
    public boolean isSneaking() {
        return sneak;
    }

    @Override
    public boolean isSprinting() {
        return sprint;
    }

    @Override
    public boolean isWet() {
        return false; // TODO
    }

    @Override
    public boolean isNoclip() {
        return false;
    }

    @Override
    public boolean onGround() {
        return onGround;
    }

    @Override
    public void kill() {
        // TODO damage 100500

    }

    @Override
    public void setInvisible(boolean flag) {
        this.invisible = flag;
    }

    @Override
    public void setSneaking(boolean flag) {
        this.sneak = flag;
    }

    @Override
    public void setSprinting(boolean flag) {
        this.sprint = flag;
    }

    @Override
    public void setPassenger(Entity entity) {
        if(getVehicle() != null && getVehicle().getRegisterID() == entity.getEntityID()){
        this.passenger = entity;
        entity.teleport(getLocation());
        entity.setVehicle(this);
        }
    }

    @Override
    public Entity getPassenger() {
        return passenger;
    }

    @Override
    public Entity getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Entity entity) {
        if(getPassenger() != null && getPassenger().getRegisterID() == entity.getEntityID()){
        this.vehicle = entity;
        entity.setPassenger(this);
        }
    }

    @Override
    public void onTick() {
        if (!prevLoc.equals(location)) {
            if (prevLoc.getBlockX() != location.getBlockX() || prevLoc.getBlockY() != location.getBlockY() || prevLoc.getBlockZ() != location.getBlockZ()) {
                this.moved = true;
            }

            if (prevLoc.getPitch() != location.getPitch() || prevLoc.getYaw() != location.getYaw()) {
                this.rotated = true;
            }

            this.prevLoc = location;
        }

        if (fireTicks > 0) {
            fireTicks--;
        }
        
        if(livedTicks > 0){
            livedTicks++;
        }
        // TODO
    }

    @Override
    public int getFireTicks() {
        return fireTicks;
    }

    @Override
    public void setFireTicks(int fireTicks) {
        this.fireTicks = fireTicks;
    }

    @Override
    public void setRegisterID(int id) {
        this.entityId = id;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.NONE;
    }

    // Not from API

    public List<Packet> getUpdatePackets() {
        // From Glowstone. Thanks dudes :D
        ArrayList<Packet> list = new ArrayList<Packet>();

        double moveX = getLocation().getX() - prevLoc.getX();
        double moveY = getLocation().getY() - prevLoc.getY();
        double moveZ = getLocation().getZ() - prevLoc.getZ();

        if (teleported || teleported && moved) {
            list.add(new ServerEntityTeleportPacket(getRegisterID(), getLocation().getX(), getLocation().getY(), getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.teleported = false;
            this.moved = false;
        } else if (moved && rotated) {
            list.add(new ServerEntityPositionRotationPacket(getRegisterID(), moveX, moveY, moveZ, getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.moved = false;
            this.rotated = false;
        } else if (moved) {
            list.add(new ServerEntityPositionPacket(getRegisterID(), moveX, moveY, moveZ, onGround()));
            this.moved = false;
        } else if (rotated) {
            list.add(new ServerEntityRotationPacket(getRegisterID(), getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.rotated = false;
        }

        return list;
    }

    public List<Packet> getSpawnPackets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getEntityID() {
        return 0;//ENTITY ID: http://minecraft.gamepedia.com/Data_values/Entity_IDs
    }

    @Override
    public int getLivedTicks() {
        return livedTicks;
    }
}
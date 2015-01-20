package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
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

    public CyanEntity() {
        //this.prevLoc = location.clone();
        //this.location = location.clone();
        // TODO
    }

    public void loadNBT(CompoundTag tag) {
    }

    public void saveNBT(CompoundTag tag) {
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
    public int getEntityID() {
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
    public boolean isInWater() {
        return getLocation().getBlock().getBlockType().getID() == 8 || getLocation().getBlock().getBlockType().getID() == 9;
    }

    @Override
    public float getEyeHeight() {
        return 0; // TODO
    }

    @Override
    public boolean isEntityInvulnerable() {
        // TODO Auto-generated method stub
        return false;
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
        // TODO
    }

    @Override
    public Entity getPassenger() {
        return null;
    }

    @Override
    public Entity getVehicle() {
        return null;
    }

    @Override
    public void setVehicle(Entity entity) {
        // TODO Auto-generated method stub

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
    public List<Packet> getUpdatePackets() {
        // From Glowstone. Thanks dudes :D
        ArrayList<Packet> list = new ArrayList<Packet>();

        double moveX = getLocation().getX() - prevLoc.getX();
        double moveY = getLocation().getY() - prevLoc.getY();
        double moveZ = getLocation().getZ() - prevLoc.getZ();

        if (teleported || teleported && moved) {
            list.add(new ServerEntityTeleportPacket(getEntityID(), getLocation().getX(), getLocation().getY(), getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.teleported = false;
            this.moved = false;
        } else if (moved && rotated) {
            list.add(new ServerEntityPositionRotationPacket(getEntityID(), moveX, moveY, moveZ, getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.moved = false;
            this.rotated = false;
        } else if (moved) {
            list.add(new ServerEntityPositionPacket(getEntityID(), moveX, moveY, moveZ, onGround()));
            this.moved = false;
        } else if (rotated) {
            list.add(new ServerEntityRotationPacket(getEntityID(), getLocation().getYaw(), getLocation().getPitch(), onGround()));
            this.rotated = false;
        }

        return list;
    }
}
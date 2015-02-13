package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.component.ComponentManager;
import net.CyanWool.api.entity.component.basics.MovementComponent;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.network.NetworkServer;

import org.spacehq.mc.protocol.data.game.EntityMetadata;
import org.spacehq.mc.protocol.data.game.values.entity.MetadataType;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
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

    private ComponentManager component;
    
    //TODO: Metadata
    protected EntityMetadata[] metadata;

    public CyanEntity(Location location) {
        this.prevLoc = location.clone();
        this.location = location.clone();
        this.component = new ComponentManager(this);
        this.metadata = new EntityMetadata[22]; //max 22 ?
        initMetadata();
        
        getComponentManager().addComponent(new MovementComponent(this));
        
        //CyanWool.getEntityManager().register(this);
        // TODO
    }

    protected void initMetadata() {
        // TODO: Metadata
        //Information about metadata: http://wiki.vg/Entities#Entity_Metadata_Format
        metadata[0] = new EntityMetadata(0, MetadataType.BYTE, 0);
        metadata[1] = new EntityMetadata(getEntityID(), MetadataType.SHORT, 0);
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
    public boolean onGround() {
        return onGround;
    }

    @Override
    public void kill() {
        // TODO damage 100500
        this.isAlive = false;
    }

    @Override
    public void setInvisible(boolean flag) {
        this.invisible = flag;
    }

    @Override
    public void onTick() {
        
        //Maybe...
        if(!isAlive){
            ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(getEntityID());
            NetworkServer.sendPacketDistance(getLocation(), packet, 32); //Test
            CyanWool.getEntityManager().unregister(this);
            return;
        }
        
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
        getComponentManager().onUpdateComponents();
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
    public EntityType getEntityType() {
        return EntityType.NONE;
    }

    @Override
    public int getEntityID() {
        return entityId;
    }

    @Override
    public int getLivedTicks() {
        return livedTicks;
    }

    @Override
    public void setEntityID(int id) {
        this.entityId = id;
    }

    @Override
    public ComponentManager getComponentManager() {
        return component;
    }

    // Not from API

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
        
        //TODO: Head update
        list.add(new ServerEntityHeadLookPacket(getEntityID(), getLocation().getYaw()));
        
        //TODO: Metadata
        list.add(new ServerEntityMetadataPacket(getEntityID(), metadata));

        return list;
    }

    public List<Packet> getSpawnPackets() {
        // TODO Auto-generated method stub
        return null;
    }
}
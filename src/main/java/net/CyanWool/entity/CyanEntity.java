package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.component.ComponentManager;
import net.CyanWool.api.entity.component.MovementComponent;
import net.CyanWool.api.entity.component.TransportComponent;
import net.CyanWool.api.entity.meta.MetaHelper;
import net.CyanWool.api.entity.meta.MetadataMap;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.entity.meta.CyanMetadataMap;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import org.spacehq.packetlib.packet.Packet;

public abstract class CyanEntity implements Entity {

    private boolean moved;
    private boolean teleported;
    private boolean rotated;

    private Location location;
    private Location prevLoc;
    protected boolean isAlive;

    private int fireTicks;
    private int entityId;
    private boolean invisible;
    private boolean onGround;
    private int livedTicks;

    private ComponentManager component;

    // TODO: Metadata
    protected MetadataMap metadata;
    private boolean moveable;

    public CyanEntity(Location location) {
        this.isAlive = true;
        this.prevLoc = location.clone();
        this.location = location.clone();
        this.component = new ComponentManager(this);
        this.metadata = new CyanMetadataMap();
        getComponentManager().addComponent(new MovementComponent(this));
        getComponentManager().addComponent(new TransportComponent(this));
        getServer().getEntityManager().register(this);
        initMetadata();
        // TODO
    }

    protected void initMetadata() {
        // TODO: Metadata
        // Information about metadata:
        // http://wiki.vg/Entities#Entity_Metadata_Format
        getMetadata().setMetadata(0, (byte) 0);
        getMetadata().setMetadata(1, (short) 0);
        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.ON_FIRE, this.fireTicks > 0);
        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.SNEAKING, isSneaking());
        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.SPRINTING, isSprinting());
        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.ARM_UP, false);// Eating,
                                                                                      // drinking,
                                                                                      // blocking
        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.INVISIBLE, this.invisible);
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
        if (getComponentManager().hasComponent("movement")) {
            MovementComponent component = (MovementComponent) getComponentManager().getComponent("movement");
            return component.isSneaking();
        }
        return false;
    }

    @Override
    public boolean isSprinting() {
        if (getComponentManager().hasComponent("movement")) {
            MovementComponent component = (MovementComponent) getComponentManager().getComponent("movement");
            return component.isSprinting();
        }
        return false;
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
        // Maybe...
        if (!isAlive) {
            ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(getEntityID());
            getServer().getNetworkServer().sendPacketDistance(getLocation(), packet, 32); // Test
            getServer().getEntityManager().unregister(this);
            return;
        }

        if (moveable) {
            if (!prevLoc.equals(location)) {
                if (prevLoc.getBlockX() != location.getBlockX() || prevLoc.getBlockY() != location.getBlockY() || prevLoc.getBlockZ() != location.getBlockZ()) {
                    this.moved = true;
                }

                if (prevLoc.getPitch() != location.getPitch() || prevLoc.getYaw() != location.getYaw()) {
                    this.rotated = true;
                }

                this.prevLoc = location;
                getServer().getEntityManager().moveToOtherLocation(this, location);
            }
        } else {
            teleport(prevLoc);
        }

        if (fireTicks > 0) {
            fireTicks--;
        }

        getMetadata().setBit(MetaHelper.STATUS, MetaHelper.StatusFlags.ON_FIRE, this.fireTicks > 0);

        if (livedTicks > 0) {
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

    @Override
    public MetadataMap getMetadata() {
        return metadata;
    }

    @Override
    public boolean isMoveable() {
        return moveable;
    }

    @Override
    public void setMoveable(boolean flag) {
        this.moveable = flag;
    }

    @Override
    public Chunk getChunk() {
        return getLocation().getChunk();
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public void setSneaking(boolean flag) {
        MovementComponent component = (MovementComponent) getComponentManager().getComponent("movement");
        component.setSneaking(flag);
    }

    @Override
    public void setSprinting(boolean flag) {
        MovementComponent component = (MovementComponent) getComponentManager().getComponent("movement");
        component.setSprinting(flag);
    }

    @Override
    public void setPassenger(Entity entity) {
        TransportComponent component = (TransportComponent) getComponentManager().getComponent("transport");
        component.setPassenger(entity);
    }

    @Override
    public Entity getPassenger() {
        TransportComponent component = (TransportComponent) getComponentManager().getComponent("transport");
        return component.getPassenger();
    }

    @Override
    public Entity getVehicle() {
        TransportComponent component = (TransportComponent) getComponentManager().getComponent("transport");
        return component.getVehicle();
    }

    @Override
    public void setVehicle(Entity entity) {
        TransportComponent component = (TransportComponent) getComponentManager().getComponent("transport");
        component.setVehicle(entity);
    }

    @Override
    public boolean canSeeEntity(Entity entity) {
        return canSeeLocation(entity.getLocation());
    }

    @Override
    public boolean canSeeLocation(Location loc) {
        double dx = Math.abs(getLocation().getX() - loc.getX());
        double dz = Math.abs(getLocation().getZ() - loc.getZ());
        return loc.getWorld() == getWorld() && dx <= (CyanWool.getServerConfiguration().getViewDistance() * 16) && dz <= (CyanWool.getServerConfiguration().getViewDistance() * 256);
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

        // TODO: Head update
        // list.add(new ServerEntityHeadLookPacket(getEntityID(),
        // getLocation().getYaw()));

        // TODO: Metadata
        list.add(new ServerEntityMetadataPacket(getEntityID(), ((CyanMetadataMap) metadata).getMetaArray()));

        return list;
    }

    // for others entities
    public abstract List<Packet> getSpawnPackets();

    @Override
    public Server getServer() {
        return CyanWool.getServer();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + entityId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CyanEntity)) {
            return false;
        }
        CyanEntity other = (CyanEntity) obj;
        if (entityId != other.entityId) {
            return false;
        }
        return true;
    }
}
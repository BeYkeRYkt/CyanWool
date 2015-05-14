package net.devwool.cyanwool.api.entity;

import java.util.Collection;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.world.Position;

public interface EntityManager {

    public Collection<Entity> getAll();

    public Entity getEntity(int id);

    public void registerEntity(Entity entity);

    public void unregisterEntity(Entity entity);

    public void moveToOtherPosition(Entity entity, Position pos);

    public Server getServer();
}
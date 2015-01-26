package net.CyanWool.api.io;

import net.CyanWool.api.entity.Entity;

public interface EntityIOService {

    public void readEntity(Entity entity);

    public void saveEntity(Entity entity);
}
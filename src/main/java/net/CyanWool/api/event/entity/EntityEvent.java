package net.CyanWool.api.event.entity;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.event.Event;

public abstract class EntityEvent extends Event {

    protected Entity entity;

    public EntityEvent(final Entity what) {
        entity = what;
    }

    public Entity getEntity() {
        return entity;
    }
}

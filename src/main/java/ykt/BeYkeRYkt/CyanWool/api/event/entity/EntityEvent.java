package ykt.BeYkeRYkt.CyanWool.api.event.entity;

import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.event.Event;

public abstract class EntityEvent extends Event {

    protected Entity entity;

    public EntityEvent(final Entity what) {
        entity = what;
    }

    public Entity getEntity() {
        return entity;
    }
}

package net.CyanWool.api.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.Server;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.Location;

public class EntityManager {

    private ConcurrentMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
    private Set<Integer> usedIds = new HashSet<Integer>();
    private int last = 0;
    private Server server;

    public EntityManager(Server server) {
        this.server = server;
    }

    public Collection<Entity> getAll() {
        return entities.values();
    }

    public Entity getEntity(int id) {
        return entities.get(id);
    }

    public synchronized void register(final Entity entity) {
        if (getEntity(entity.getEntityID()) != null) {
            return; // IMPOSIBBLEEE
        }

        int startedAt = last;
        for (int id = last + 1; id != startedAt; ++id) {
            if (id == -1 || id == 0)
                continue;

            if (usedIds.add(id)) {
                entity.setEntityID(id);
                last = id;
                break;
            }
        }
        entities.put(entity.getEntityID(), entity);
        Location loc = entity.getLocation();
        loc.getChunk().getEntities().add(entity);
        loc.getWorld().getEntities().add(entity);
        // REMOVED//
        // getServer().getScheduler().runTaskRepeat(new Runnable() {
        // @Override
        // public void run() {
        // //getServer().broadcastMessage("fuu");
        // entity.onTick();
        // }
        // }, 1, 1);
    }

    public synchronized void unregister(Entity entity) {
        entities.remove(entity.getEntityID());
        usedIds.remove(entity.getEntityID());

        // Iterator<Entity> it =
        // entity.getLocation().getChunk().getEntities().iterator();
        // while (it.hasNext()) {
        // Entity ent = it.next();
        // if (ent.getEntityID() == entity.getEntityID()) {
        // it.remove();
        // }
        // }
        for (Entity ent : entity.getLocation().getChunk().getEntities()) {
            if (ent.getEntityID() == entity.getEntityID()) {
                entity.getLocation().getChunk().getEntities().remove(ent);
                break;
            }
        }

        // Iterator<Entity> it2 = entity.getWorld().getEntities().iterator();
        // while (it2.hasNext()) {
        // Entity ent = it2.next();
        // if (ent.getEntityID() == entity.getEntityID()) {
        // it2.remove();
        // }
        // }

        for (Entity ent : entity.getWorld().getEntities()) {
            if (ent.getEntityID() == entity.getEntityID()) {
                entity.getWorld().getEntities().remove(ent);
                break;
            }
        }
    }

    public void moveToOtherLocation(Entity entity, Location loc) {
        Chunk prev = entity.getLocation().getChunk();
        Chunk next = loc.getChunk();

        if (!prev.equals(next)) {
            // Iterator<Entity> it = prev.getEntities().iterator();
            // while (it.hasNext()) {
            // Entity ent = it.next();
            // if (ent.getEntityID() == entity.getEntityID()) {
            // it.remove();
            // }
            // }
            for (Entity ent : prev.getEntities()) {
                if (ent.getEntityID() == entity.getEntityID()) {
                    prev.getEntities().remove(ent);
                    break;
                }
            }

            next.getEntities().add(entity);
        }
    }

    public Server getServer() {
        return server;
    }
}
package net.CyanWool.api.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.CyanWool.api.Server;
import net.CyanWool.api.io.WorldIOService;
import net.CyanWool.api.theards.WorldThread;

public class WorldManager {

    private List<World> worlds;
    private Server server;
    private final List<WorldEntry> worldsEntry = new ArrayList<WorldEntry>();
    private WorldIOService service;

    public WorldManager(Server server, WorldIOService service) {
        this.worlds = new ArrayList<World>();
        this.server = server;
        this.service = service;
    }

    public Server getServer() {
        return server;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public void loadWorld(final World world) {
        service.readWorld(world);
    }

    public boolean addWorld(World world) {
        if (worlds.isEmpty()) {
            worlds.add(world);

            WorldEntry we = new WorldEntry(world);
            WorldThread thread = new WorldThread(world);
            we.setTask(thread);
            worldsEntry.add(we);
            we.getTask().start();
            server.getLogger().info("Added new world: " + world.getName());
            return true;
        } else {
            for (World w : worlds) {
                if (!w.getName().equals(world.getName())) {
                    worlds.add(world);

                    WorldEntry we = new WorldEntry(world);
                    WorldThread thread = new WorldThread(world);
                    we.setTask(thread);
                    worldsEntry.add(we);
                    we.getTask().start();
                    server.getLogger().info("Added new world: " + world.getName());
                    return true;
                }
            }
        }
        return false;
    }

    public World getWorld(String name) {
        for (World w : worlds) {
            if (!w.getName().equals(name)) {
                return w;
            }
        }
        return null;
    }

    public boolean removeWorld(final World world) {
        if (stopWorldEntry(world) && stopWorld(world)) {
            // Save
            // world.saveAll();
            // ???
            service.saveWorld(world);
            return true;
        }
        return false;
    }

    // Needed ?
    private boolean stopWorld(World world) {
        Iterator<World> it = worlds.iterator();
        while (it.hasNext()) {
            World w = it.next();
            if (w.getName().equals(world.getName())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private boolean stopWorldEntry(World world) {
        Iterator<WorldEntry> it = worldsEntry.iterator();
        while (it.hasNext()) {
            WorldEntry w = it.next();
            if (w.getWorld().getName().equals(world.getName())) {
                w.getTask().shutdown();
                it.remove();
                return true;
            }
        }
        return false;
    }
}
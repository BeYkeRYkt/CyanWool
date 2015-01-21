package net.CyanWool.api.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.CyanWool.CyanServer;
import net.CyanWool.api.io.WorldIOService;
import net.CyanWool.api.theards.WorldServiceThread;
import net.CyanWool.api.theards.WorldThread;

public class WorldManager {

    private List<World> worlds;
    private CyanServer server;
    private final List<WorldEntry> worldsEntry = new CopyOnWriteArrayList<WorldEntry>();
    private WorldIOService service;

    public WorldManager(CyanServer server, WorldIOService service) {
        this.worlds = new ArrayList<World>();
        this.server = server;
        this.service = service;
    }

    public CyanServer getServer() {
        return server;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public World loadWorld(String name){
        WorldServiceThread thread = new WorldServiceThread(service, name, false);
        thread.start();
        return thread.getResult();
    }
    
    public boolean addWorld(World world) {
        for (World w : worlds) {
            if (!w.getName().equals(world.getName())) {
                worlds.add(w);

                WorldEntry we = new WorldEntry(world);
                WorldThread thread = new WorldThread(world);
                we.setTask(thread);
                worldsEntry.add(we);
                we.getTask().start();
                return true;
            }
        }
        return false;
    }
    
    public World getWorld(String name){
        for (World w : worlds) {
            if (!w.getName().equals(name)) {
                return w;
            }
        }
        return null;
    }

    public boolean removeWorld(World world) {
        if (stopWorldEntry(world) && stopWorld(world)) {
            // Save
            // world.saveAll();
            WorldServiceThread thread = new WorldServiceThread(service, world.getName(), true);
            thread.start();
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
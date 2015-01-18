package ykt.BeYkeRYkt.CyanWool.api.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ykt.BeYkeRYkt.CyanWool.CyanServer;
import ykt.BeYkeRYkt.CyanWool.api.theards.WorldThread;
import ykt.BeYkeRYkt.CyanWool.world.CyanChunk;

public class WorldManager {

    private List<World> worlds;
    private CyanServer server;
    private final List<WorldEntry> worldsEntry = new CopyOnWriteArrayList<WorldEntry>();

    public WorldManager(CyanServer server) {
        this.worlds = new ArrayList<World>();
        this.server = server;
    }

    public CyanServer getServer() {
        return server;
    }

    public List<World> getWorlds() {
        return worlds;
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

    public boolean removeWorld(World world) {
        if (stopWorldEntry(world) && stopWorld(world)) {
            // Save
            // world.saveAll();
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

    public boolean chunkExists(World world, int x, int z){
        return false;
    }

    public Chunk loadChunk(World world, int x, int z){
        return new CyanChunk(world, x, z);
    }

    public void saveChunk(Chunk chunk){
        //TODO
    }
}
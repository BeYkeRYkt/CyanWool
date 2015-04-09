package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.io.ChunkIOService;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.ChunkCoords;
import net.CyanWool.api.world.ChunkManager;
import net.CyanWool.api.world.World;

public class CyanChunkManager implements ChunkManager {

    private World world;
    private ConcurrentMap<ChunkCoords, Chunk> chunks;
    private ChunkIOService service;
    private boolean spawnLoaded;

    public CyanChunkManager(World world, ChunkIOService service) {
        this.world = world;
        this.service = service;
        this.chunks = new ConcurrentHashMap<ChunkCoords, Chunk>();
    }

    @Override
    public Chunk getChunk(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        if (chunks.containsKey(coords)) {
            return chunks.get(coords);
        } else {
            loadChunk(x, z);
            return chunks.get(coords);
        }
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        return chunks.containsKey(coords) && chunks.get(coords).isLoaded();
    }

    @Override
    public boolean isChunkInUse(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        Chunk chunk = chunks.get(coords);
        return chunk != null && !chunk.getPlayers().isEmpty();
    }

    @Override
    public boolean loadChunk(int x, int z) {
        Chunk chunk = service.readChunk(x, z);
        ChunkCoords coords = new ChunkCoords(x, z);
        chunks.put(coords, chunk);

        boolean generate = chunk.isNeedGenerate();

        if (!generate) {
            return true;
        }
        generateChunk(chunk);
        return true;
    }

    @Override
    public void unloadChunk(Chunk chunk) {
        ChunkCoords coords = new ChunkCoords(chunk.getX(), chunk.getZ());
        chunks.remove(coords);
        service.saveChunk(chunk);
    }

    @Override
    public void generateChunk(Chunk chunk) {
        world.getGenerator().generate(chunk.getX(), chunk.getZ());
    }

    @Override
    public List<Chunk> getLoadedChunks() {
        ArrayList<Chunk> result = new ArrayList<Chunk>();
        for (Chunk chunk : chunks.values()) {
            if (chunk.isLoaded()) {
                result.add(chunk);
            }
        }
        return result;
    }

    @Override
    public Chunk getChunkFromChunkCoords(int x, int z) {
        return getChunk(x, z);
    }

    @Override
    public Chunk getChunkFromBlockCoords(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    @Override
    public void saveChunks() {
        for (Chunk chunk : getLoadedChunks()) {
            unloadChunk(chunk);
        }
    }

    @Override
    public void onTick() {
        // Auto unload chunks
        for (Chunk chunk : getLoadedChunks()) {
            if (!chunk.isLocked() && chunk.getUsedPlayers() == 0) {
                unloadChunk(chunk);
            }
        }
    }

    @Override
    public void loadSpawnChunks() {
        int centerX = world.getSpawnLocation().getBlockX() >> 4;
        int centerZ = world.getSpawnLocation().getBlockZ() >> 4;
        int radius = 4 * 8 / 3;
        long loadTime = System.currentTimeMillis();
        int total = (radius * 2 + 1) * (radius * 2 + 1), current = 0;
        for (int x = centerX - radius; x <= centerX + radius; ++x) {
            for (int z = centerZ - radius; z <= centerZ + radius; ++z) {
                ++current;
                loadChunk(x, z);
                getChunk(x, z).setLocked(true);
                // spawnChunkLock.acquire(new GlowChunk.Key(x, z));
                if (System.currentTimeMillis() >= loadTime + 1000) {
                    int progress = 100 * current / total;
                    CyanWool.getLogger().info("Preparing spawn for " + world.getName() + ": " + progress + "%");
                    loadTime = System.currentTimeMillis();
                }
            }
        }
        spawnLoaded = true;
    }

    @Override
    public boolean isLoadedSpawnChunks() {
        return spawnLoaded;
    }
}
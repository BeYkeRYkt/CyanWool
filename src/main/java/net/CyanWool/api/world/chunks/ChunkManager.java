package net.CyanWool.api.world.chunks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.io.services.ChunkIOService;
import net.CyanWool.api.world.World;

public class ChunkManager {

    private ChunkIOService service;
    private World world;
    private ChunkGenerator generator;
    private ConcurrentMap<ChunkCoords, Chunk> chunks = new ConcurrentHashMap<ChunkCoords, Chunk>();

    public ChunkManager(World world, ChunkIOService service, ChunkGenerator generator) {
        this.world = world;
        this.service = service;
        this.generator = generator;
    }

    public ChunkGenerator getGenerator() {
        return generator;
    }

    public Chunk getChunk(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        if (chunks.containsKey(coords)) {
            return chunks.get(coords);
        }
        return null;// Maybe generate
    }

    public boolean isChunkLoaded(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        return chunks.containsKey(coords) && chunks.get(coords).isLoaded();
    }

    public boolean isChunkInUse(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        Chunk chunk = chunks.get(coords);
        return chunk != null && chunk.getPlayers().isEmpty();
    }

    public boolean loadChunk(int x, int z, boolean generate) {
        //if (service.read(x, z)) {
            // Start event...
        //}

        if (!generate) {
            return false;
        }

        return true;
    }

    public void saveChunk(Chunk chunk) {
        // TODO
        service.save(chunk.getX(), chunk.getZ());
    }

    public void generateChunk(int x, int z) {
        getGenerator().generate(world, x, z);
    }

    public List<Chunk> getLoadedChunks() {
        ArrayList<Chunk> result = new ArrayList<Chunk>();
        for (Chunk chunk : chunks.values()) {
            if (chunk.isLoaded()) {
                result.add(chunk);
            }
        }
        return result;
    }
}
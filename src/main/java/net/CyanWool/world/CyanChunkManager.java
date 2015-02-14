package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.io.ChunkIOService;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.ChunkCoords;
import net.CyanWool.api.world.ChunkManager;
import net.CyanWool.api.world.World;

public class CyanChunkManager implements ChunkManager{

    private World world;
    private ConcurrentMap<ChunkCoords, Chunk> chunks;
    private ChunkIOService service;

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
             CyanChunk chunk = (CyanChunk) service.readChunk(x, z);
             CyanChunk prev = (CyanChunk) chunks.putIfAbsent(coords, chunk);
             return prev == null ? chunk : prev;
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
        return chunk != null && chunk.getPlayers().isEmpty();
    }

    @Override
    public boolean loadChunk(int x, int z, boolean generate) {
        Chunk chunk = service.readChunk(x, z);
        ChunkCoords coords = new ChunkCoords(x, z);
        chunks.put(coords, chunk);

        if (!generate) {
            return false;
        }
        generateChunk(chunk);
        return true;
    }

    @Override
    public void saveChunk(final Chunk chunk) {
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
            saveChunk(chunk);
        }
    }
}
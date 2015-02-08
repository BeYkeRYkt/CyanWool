package net.CyanWool.api.world.chunks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.io.ChunkIOService;
import net.CyanWool.api.world.World;
import net.CyanWool.world.CyanChunk;

public class ChunkManager {

    private World world;
    private ChunkGenerator generator;
    private ConcurrentMap<ChunkCoords, Chunk> chunks;
    private ChunkIOService service;

    public ChunkManager(World world, ChunkIOService service, ChunkGenerator generator) {
        this.world = world;
        this.service = service;
        this.generator = generator;
        this.chunks = new ConcurrentHashMap<ChunkCoords, Chunk>();
    }

    public ChunkGenerator getGenerator() {
        return generator;
    }
    
    public void setGenerator(ChunkGenerator generator){
        this.generator = generator;
    }

    public Chunk getChunk(int x, int z) {
        ChunkCoords coords = new ChunkCoords(x, z);
        if (chunks.containsKey(coords)) {
            return chunks.get(coords);
        }else{
        CyanChunk chunk = new CyanChunk(world, x, z);
        CyanChunk prev = (CyanChunk) chunks.putIfAbsent(coords, chunk);
        return prev == null ? chunk : prev;
        }
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
        Chunk chunk = service.readChunk(x, z);
        ChunkCoords coords = new ChunkCoords(x, z);
        chunks.put(coords, chunk);
        ((CyanChunk) chunk).setLoaded(true);
        CyanWool.getLogger().info("Loaded chunk x:" + x + ", z: " + z);

        if (!generate) {
        return false;
        }
        generateChunk(chunk);
        return true;
    }

    public void saveChunk(final Chunk chunk) {
        ChunkCoords coords = new ChunkCoords(chunk.getX(), chunk.getZ());
        chunks.remove(coords);
        service.saveChunk(chunk);
    }

    public void generateChunk(Chunk chunk) {
        getGenerator().generate(world, chunk);
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

    public Chunk getChunkFromChunkCoords(int x, int z) {
        return getChunk(x, z);
    }

    public Chunk getChunkFromBlockCoords(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    public void saveChunks() {
        for(Chunk chunk: getLoadedChunks()){
            saveChunk(chunk);
        }
    }
}
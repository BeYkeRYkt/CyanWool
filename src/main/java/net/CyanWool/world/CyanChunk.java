package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Register;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.ChunkCoords;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.block.CyanBlock;

public class CyanChunk implements Chunk {

    private World world;
    private int x;
    private int z;

    // private final List<Entity> entities = new ArrayList<Entity>();
    private final List<Entity> entities = new CopyOnWriteArrayList<Entity>();
    private boolean isLoaded;
    private Section[] sections;
    private byte[] biomes;
    private boolean needGenerate;
    private boolean lockedInMemory;
    private int usedPlayers;

    public CyanChunk(World world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
        this.sections = new Section[16];
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        int id = getSection(y).getBlocks().getBlock(x, y, z);
        int data = getSection(y).getBlocks().getData(x, y, z);
        // Checking...
        if (getSection(y).getNotSupportData().getData(x, y, z) != 0) {
            data = getSection(y).getNotSupportData().getData(x, y, z);
        }
        BlockType type = Register.getBlock(id, data);
        Location location = new Location(world, x, y, z);
        CyanBlock block = new CyanBlock(location, type);
        return block;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Entity entity : entities) {
            if (entity instanceof Player) {// Other checker...
                Player player = (Player) entity;
                players.add(player);
            }
        }

        return players;
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        return getSection(y).getSkyLight().get(x, y, z);
    }

    @Override
    public void setSkyLight(int x, int y, int z, int skylight) {
        getSection(y).getSkyLight().set(x, y, z, skylight);
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return getSection(y).getBlockLight().get(x, y, z);
    }

    @Override
    public void setBlockLight(int x, int y, int z, int blocklight) {
        getSection(y).getBlockLight().set(x, y, z, blocklight);
    }

    @Override
    public void setBlock(int x, int y, int z, int type) {
        getSection(y).getBlocks().setBlock(x, y, z, type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        int data = type.getData();
        getSection(y).getNotSupportData().setData(x, y, z, 0);
        if (data > type.getMaxData()) {
            getSection(y).getNotSupportData().setData(x, y, z, data);
            data = 0;
        }
        getSection(y).getBlocks().setBlockAndData(x, y, z, type.getID(), data);
    }

    @Override
    public void loadChunk() {
        // TODO Auto-generated method stub
    }

    @Override
    public void unloadChunk() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof CyanChunk)) {
            return false;
        } else {
            CyanChunk chunk = (CyanChunk) other;
            return this.getX() == chunk.getX() && this.getZ() == chunk.getZ();
        }
    }

    // NOT API

    // From Glowstone. Thanks dude's :)
    public static final int WIDTH = 16, HEIGHT = 16, DEPTH = 256;
    private static final int SEC_DEPTH = 16;

    public void initializeSections(Section[] initSections) {
        if (isLoaded()) {
            CyanWool.getLogger().info("Tried to initialize already loaded chunk (" + x + "," + z + ")");
            return;
        }
        if (initSections != null) {
            for (int i = 0; i < initSections.length; i++) {
                sections[i] = initSections[i];
            }
        }

        biomes = new byte[WIDTH * HEIGHT];
        // heightMap = new byte[WIDTH * HEIGHT];
        // tile entity initialization
        // for (int i = 0; i < sections.length; ++i) {
        // if (sections[i] == null)
        // continue;
        // int by = 16 * i;
        // for (int cx = 0; cx < WIDTH; ++cx) {
        // for (int cz = 0; cz < HEIGHT; ++cz) {
        // for (int cy = by; cy < by + 16; ++cy) {
        // // createEntity(cx, cy, cz, getType(cx, cz, cy));
        // }
        // }
        // }
        // }
    }

    private Section getSection(int y) {
        int idx = y >> 4;
        if (y < 0 || y >= DEPTH || !this.isLoaded || idx >= this.sections.length) {
            return null;
        }
        return sections[idx];
    }

    public Section[] getSections() {
        return sections;
    }

    @Override
    public int getMaxHeight() {
        return 256;
    }

    @Override
    public void setLoaded(boolean b) {
        this.isLoaded = b;
    }

    @Override
    public byte[] getBiomeData() {
        return biomes;
    }

    @Override
    public void setNeedGenerate(boolean flag) {
        this.needGenerate = flag;
    }

    @Override
    public boolean isNeedGenerate() {
        return needGenerate;
    }

    @Override
    public void setBlock(int x, int y, int z, int type, int data) {
        BlockType btype = Register.getBlock(type, data);
        this.setBlock(x, y, z, btype);
    }

    @Override
    public boolean isLocked() {
        return lockedInMemory;
    }

    @Override
    public void setLocked(boolean flag) {
        this.lockedInMemory = flag;
    }

    @Override
    public ChunkCoords getChunkCoords() {
        return new ChunkCoords(x, z);
    }

    @Override
    public int getUsedPlayers() {
        return usedPlayers;
    }

    public void addPlayer() {
        usedPlayers++;
    }

    public void removePlayer() {
        usedPlayers--;
    }

    @Override
    public void setData(int x, int y, int z, int data) {
        Block block = getBlock(x, y, z);
        setBlock(x, y, z, block.getBlockType());
    }

    @Override
    public int getData(int x, int y, int z) {
        return getBlock(x, y, z).getBlockType().getData();
    }

    @Override
    public void setData(Location location, int data) {
        setData(location.getBlockX(), location.getBlockY(), location.getBlockZ(), data);
    }

    @Override
    public int getData(Location location) {
        return getData(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Register;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.block.CyanBlock;

public class CyanChunk implements Chunk {

    private World world;
    private int x;
    private int z;

    private final List<Entity> entities = new ArrayList<Entity>();
    private boolean isLoaded;
    private org.spacehq.mc.protocol.data.game.Chunk[] sections;

    public CyanChunk(World world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
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
    public int getType(int x, int z, int y) {
        return getSection(y).getBlocks().getBlock(x, y, z);
    }

    @Override
    public void setType(int x, int z, int y, int type) {
        getSection(y).getBlocks().setBlock(x, y, z, type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        getSection(y).getBlocks().setBlockAndData(x, y, z, type.getID(), type.getData());
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

    public void initializeSections(org.spacehq.mc.protocol.data.game.Chunk[] initSections) {
        if (isLoaded()) {
            CyanWool.getLogger().info("Tried to initialize already loaded chunk (" + x + "," + z + ")");
            return;
        }
        sections = new org.spacehq.mc.protocol.data.game.Chunk[DEPTH / SEC_DEPTH];
        System.arraycopy(initSections, 0, sections, 0, Math.min(sections.length, initSections.length));
        // biomes = new byte[WIDTH * HEIGHT];
        // heightMap = new byte[WIDTH * HEIGHT];
        // tile entity initialization
        for (int i = 0; i < sections.length; ++i) {
            if (sections[i] == null)
                continue;
            int by = 16 * i;
            for (int cx = 0; cx < WIDTH; ++cx) {
                for (int cz = 0; cz < HEIGHT; ++cz) {
                    for (int cy = by; cy < by + 16; ++cy) {
                        // createEntity(cx, cy, cz, getType(cx, cz, cy));
                    }
                }
            }
        }
    }

    private org.spacehq.mc.protocol.data.game.Chunk getSection(int y) {
        int idx = y >> 4;
        if (y < 0 || y >= DEPTH || !isLoaded || idx >= sections.length) {
            return null;
        }
        return sections[idx];
    }

    public org.spacehq.mc.protocol.data.game.Chunk[] getSections() {
        return sections;
    }
}
package net.CyanWool.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

import net.CyanWool.api.io.ChunkIOService;
import net.CyanWool.api.io.RegionFile;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.world.CyanChunk;

import org.spacehq.mc.protocol.data.game.NibbleArray3d;
import org.spacehq.mc.protocol.data.game.ShortArray3d;
import org.spacehq.opennbt.NBTIO;
import org.spacehq.opennbt.tag.builtin.ByteArrayTag;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.ListTag;

public class CyanChunkIOService implements ChunkIOService {

    private static final int REGION_SIZE = 32;
    private final World world;
    private final File dir;
    private final RegionCache cache = new RegionCache(".mca");

    public CyanChunkIOService(World world) {
        this.world = world;
        this.dir = new File(world.getPath());
    }

    @Override
    public Chunk readChunk(int x, int z) {
        try {
            RegionFile region;
            CyanChunk chunk = new CyanChunk(world, x, z);
            region = cache.getRegionFile(dir, x, z);
            int regionX = x & (REGION_SIZE - 1);
            int regionZ = z & (REGION_SIZE - 1);
            if (!region.hasChunk(regionX, regionZ)) {
                return null;
            }
            DataInputStream in = region.getChunkDataInputStream(regionX, regionZ);
            if (in != null) {
                CompoundTag compoundTag = null;
                try {
                    compoundTag = (CompoundTag) NBTIO.readTag(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (compoundTag == null)
                    return null;

                CompoundTag level = compoundTag.get("Level");
                ListTag sections = level.get("Sections");
                org.spacehq.mc.protocol.data.game.Chunk[] chunks = new org.spacehq.mc.protocol.data.game.Chunk[16];
                for (int i = 0; i < sections.size(); i++) {
                    CompoundTag chunkz = sections.get(i);
                    ByteArrayTag blocks = chunkz.get("Blocks");
                    ByteArrayTag blockLight = chunkz.get("BlockLight");
                    ByteArrayTag skyLight = chunkz.get("SkyLight");
                    ByteArrayTag data = chunkz.get("Data");
                    ByteArrayTag add = chunkz.get("Add");
                    ShortArray3d block = new ShortArray3d(4096);
                    for (int cX = 0; cX < 16; cX++)
                        for (int cY = 0; cY < 16; cY++)
                            for (int cZ = 0; cZ < 16; cZ++) {
                                int index = 256 * cY + 16 * cZ + cX;
                                int id = blocks.getValue(index) + (add != null ? getValue(add, index) << 8 : 0);
                                block.setBlockAndData(cX, cY, cZ, id + (id < 0 ? 256 : 0), getValue(data, index));
                            }
                    chunks[i] = new org.spacehq.mc.protocol.data.game.Chunk(block, new NibbleArray3d(blockLight.getValue()), new NibbleArray3d(skyLight.getValue()));
                }
                
                //from Glowstone
                chunk.initializeSections(chunks);
                return chunk;
            } else {
                // todo: generate chunk
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private int getValue(ByteArrayTag array, int index) {
        return (index % 2 == 0 ? array.getValue(index / 2) : array.getValue(index / 2) >> 4) & 0x0F;
    }

    @Override
    public void saveChunk(Chunk chunk) {
        // TODO Auto-generated method stub

    }

}
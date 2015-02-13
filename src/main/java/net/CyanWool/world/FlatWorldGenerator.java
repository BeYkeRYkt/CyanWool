package net.CyanWool.world;

import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldGenerator;


public class FlatWorldGenerator implements WorldGenerator {

    private World world;
    
    public FlatWorldGenerator(World world){
        this.world = world;
    }
    
    @Override
    public Chunk generate(int chunkX, int chunkZ) {
        CyanChunk chunk = new CyanChunk(null, chunkX, chunkZ);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 16; y++) {
                    int id = 0;
                    if (y == 60)
                        id = 2;
                    else if (y >= 55 && y < 60)
                        id = 3;
                    else if (y == 0)
                        id = 7; //Bedrock
                    else if (y < 55)
                        id = 1;

                    chunk.setType(x, z, y, id);
                    //chunk.setMetaData(x, z, y, 0);
                    chunk.setBlockLight(x, z, y, 0);
                    chunk.setSkyLight(x, z, y, 15);
                }
            }
        }
        return chunk;
    }

}

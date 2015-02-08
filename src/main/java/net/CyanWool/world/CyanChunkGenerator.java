package net.CyanWool.world;

import java.util.List;
import java.util.Random;

import net.CyanWool.api.Register;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkGenerator;
import net.CyanWool.api.world.chunks.ChunkManager;

public class CyanChunkGenerator implements ChunkGenerator {

    private ChunkManager manager;

    public CyanChunkGenerator(ChunkManager manager) {
        this.manager = manager;
    }

    @Override
    public List getDecorators() {
        return null;
    }

    @Override
    public void generate(World world, Chunk chunk) {
        for (int cX = 0; cX < 16; cX++) {
            for (int cY = 0; cY < chunk.getMaxHeight(); cY++) {
                for (int cZ = 0; cZ < 16; cZ++) {
                    if(cY == 1){
                        //Setting bedrock
                        BlockType type = Register.getBlock(7);
                        chunk.setBlock(cX, cY, cZ, type);
                    }else if(cY == 2){
                        //Setting grass
                        BlockType type = Register.getBlock(2);
                        chunk.setBlock(cX, cY, cZ, type);
                    }else{
                        //Setting air
                        BlockType type = Register.getBlock(0);
                        chunk.setBlock(cX, cY, cZ, type);
                    }
                }
            }
        }

    }

    @Override
    public void generate(World world,Chunk chunk, Random random) {
        this.generate(world, chunk);
    }

}
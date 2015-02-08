package net.CyanWool.api.world.chunks;

import java.util.List;
import java.util.Random;

import net.CyanWool.api.world.World;

public interface ChunkGenerator {

    public List getDecorators(); // Decorator class

    public void generate(World world, Chunk chunk);

    public void generate(World world, Chunk chunk, Random random);

    // public boolean canSpawnDecorator(); //TODO:
}
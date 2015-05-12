package net.devwool.cyanwool.api.world;

public interface World {

    public Block getBlock(Position position);
    
    public Block getBlock(int x, int y, int z);

}
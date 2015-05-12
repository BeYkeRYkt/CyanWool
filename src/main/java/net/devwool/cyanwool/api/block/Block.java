package net.devwool.cyanwool.api.block;

import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.chunk.Chunk;

public interface Block {
    
    public Position getPosition();
    
    public Chunk getChunk();
    
    public void destroyBlock();
    
    public int getBlockLight();
    
    public void transformToFallingBlock();
    
    public void dropBlock();
    
    public void setBlock(BlockType type);
	
	public BlockType getBlockType();
}
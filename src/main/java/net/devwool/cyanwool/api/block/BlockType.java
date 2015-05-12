package net.devwool.cyanwool.api.block;

import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

import org.spacehq.mc.protocol.data.game.values.world.Sound;

public interface BlockType {
    
    public String getStringId();
    
    public int getId();
    
    public Sound getStepSound();
    
    public int getLightLevel();
    
    public void setLightLevel(int level);
    
    public float getResistance();
    
    public void setResistance(float resistance);
    
    public void setHardness(float hardness);
    
    public float getHardness();
    
    public float setUnbreakable(boolean flag);
    
    public float isUnbreakable();
    
    public void updateTick();
    
    public void onPlayerBreakBlock(World world, Position pos, Player player);
    
    public void onPlayerPlaceBlock(World world, Position pos, Player player);
    
    public void dropBlock();
    
    public void spawnDrop(World world, Position pos, ItemStack stack);
    
    
}
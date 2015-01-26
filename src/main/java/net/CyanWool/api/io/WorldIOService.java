package net.CyanWool.api.io;

import net.CyanWool.api.world.World;

public interface WorldIOService {

    public void readWorld(World world);

    public void saveWorld(World world);

}
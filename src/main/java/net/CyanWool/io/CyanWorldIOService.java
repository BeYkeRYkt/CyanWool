package net.CyanWool.io;

import java.io.File;
import java.io.IOException;

import net.CyanWool.api.io.WorldIOService;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

import org.spacehq.opennbt.NBTIO;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.IntTag;

public class CyanWorldIOService implements WorldIOService {

    @Override
    public void readWorld(World world) {
        CompoundTag tag;
        try {
            tag = NBTIO.readFile(new File(world.getPath() + "/level.dat"));
            CompoundTag data = tag.get("Data");
            IntTag xTag = data.get("SpawnX");
            IntTag yTag = data.get("SpawnY");
            IntTag zTag = data.get("SpawnZ");

            // World world = new CyanWorld(name, new CyanPlayerIOService());
            Location newSpawn = new Location(world, xTag.getValue(), yTag.getValue(), zTag.getValue());
            world.setSpawnLocation(newSpawn);
            world.setWorldTime(0);// Demo test
            // world.getChunkManager().loadChunk(newSpawn.getBlockX() >> 4,
            // newSpawn.getBlockZ() >> 4, false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void saveWorld(World world) {
        // TODO Auto-generated method stub

    }
}
package net.CyanWool;

import net.CyanWool.api.Register;
import net.CyanWool.api.Server;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.packs.ServerPack;
import net.CyanWool.api.world.World;
import net.CyanWool.block.blocks.BlockAir;
import net.CyanWool.block.blocks.BlockBedrock;
import net.CyanWool.block.blocks.BlockDirt;
import net.CyanWool.block.blocks.BlockGrass;
import net.CyanWool.block.blocks.BlockGrassTest;
import net.CyanWool.inventory.items.ItemBedrock;
import net.CyanWool.inventory.items.ItemDirt;
import net.CyanWool.inventory.items.ItemGrass;
import net.CyanWool.io.CyanPlayerIOService;
import net.CyanWool.world.CyanWorld;

public class MinecraftServerPack implements ServerPack {

    private Server server;

    public MinecraftServerPack(Server server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return "MinecraftVanilla";
    }

    @Override
    public void registerItems() {
        Register.registerItem(new ItemGrass());
        Register.registerItem(new ItemDirt());
        Register.registerItem(new ItemBedrock());
    }

    @Override
    public void registerBlocks() {
        Register.registerBlock(new BlockAir());
        Register.registerBlock(new BlockDirt());
        Register.registerBlock(new BlockGrass());
        Register.registerBlock(new BlockBedrock());
        Register.registerBlock(new BlockGrassTest());
    }

    @Override
    public void registerWorlds() {
        World world = new CyanWorld("world", new CyanPlayerIOService());
        server.getWorldManager().loadWorld(world);
        server.getWorldManager().addWorld(world);
    }

    @Override
    public void reigsterRecipes() {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerEnchants() {
        // TODO Auto-generated method stub

    }

    // @Override
    public void registerThreads() {
        // World thread
        server.getScheduler().runTaskRepeat(new Runnable() {

            @Override
            public void run() {
                for (World world : server.getWorldManager().getWorlds()) {
                    world.onTick();
                }
            }
        }, 1, 1);

        // Entity thread
        server.getScheduler().runTaskRepeat(new Runnable() {

            @Override
            public void run() {
                for (Entity entity : server.getEntityManager().getAll()) {
                    entity.onTick();
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, 1, 1);
    }

}
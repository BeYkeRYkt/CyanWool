package net.CyanWool.api.theards;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.io.WorldIOService;
import net.CyanWool.api.world.World;

public class WorldServiceThread extends Thread {

    private WorldIOService service;
    private String name;
    private World result;
    private boolean save;
    
    public WorldServiceThread(WorldIOService service, String name, boolean isSave) {
        this.name = name;
        this.save = isSave;
        setName("CyanWorldService-" + name);
    }

    public World getResult(){
        return result;
    }
    
    @Override
    public void run() {
        if(save){
        //Maybe recode
        World world = CyanWool.getWordManager().getWorld(name);
        service.save(world);
        }else{
        result = service.read(name);
        }
    }

}
package net.CyanWool.api.theards;

import net.CyanWool.api.io.ChunkIOService;
import net.CyanWool.api.world.chunks.Chunk;


public class ChunkServiceThread extends Thread {

    private int x;
    private int z;
    private ChunkIOService service;
    private boolean save;
    private Chunk result;

    public ChunkServiceThread(ChunkIOService service, int x, int z, boolean isSave) {
        this.service = service;
        this.x = x;
        this.z = z;
        this.save = isSave;
        setName("CyanChunkService-" + x + z);
    }
    
    public Chunk getResult(){
        return result;
    }

    @Override
    public void run() {
        if(save){
        service.save(x, z);
        }else{
        result = service.read(x, z);//?
        }
    }

}
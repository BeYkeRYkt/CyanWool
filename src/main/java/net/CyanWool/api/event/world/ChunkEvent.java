package net.CyanWool.api.event.world;


public abstract class ChunkEvent extends WorldEvent {

    protected Chunk chunk;

    protected ChunkEvent(final Chunk chunk) {
        super(chunk.getWorld());
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}

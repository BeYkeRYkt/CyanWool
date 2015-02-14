package net.CyanWool.world;

import org.spacehq.mc.protocol.data.game.Chunk;
import org.spacehq.mc.protocol.data.game.NibbleArray3d;
import org.spacehq.mc.protocol.data.game.ShortArray3d;

public class Section extends Chunk {

    private final int y;

    public Section(int y, ShortArray3d blocks, NibbleArray3d blocklight, NibbleArray3d skylight) {
        super(blocks, blocklight, skylight);
        this.y = y;
    }

    public int getY() {
        return y;
    }

    
}
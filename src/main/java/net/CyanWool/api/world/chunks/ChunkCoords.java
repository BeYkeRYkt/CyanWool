package net.CyanWool.api.world.chunks;


public class ChunkCoords {

    private final int x;
    private final int z;

    public ChunkCoords(int x, int z) {
        this.x = x;
        this.z = z;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "ChunkCoords{" + "x=" + getX() + "z=" + getZ() + '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ChunkCoords)) {
            return false;
        } else {
            ChunkCoords chunk = (ChunkCoords) other;
            return this.getX() == chunk.getX() && this.getZ() == chunk.getZ();
        }
    }
}
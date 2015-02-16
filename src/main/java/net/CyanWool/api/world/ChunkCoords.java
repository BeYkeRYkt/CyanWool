package net.CyanWool.api.world;

public class ChunkCoords {

    private final int x;
    private final int z;

    public ChunkCoords(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "ChunkCoords{" + "x=" + getX() + "z=" + getZ() + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || getClass() != other.getClass()) {
            return false;
        } else {
            ChunkCoords chunk = (ChunkCoords) other;
            return getX() == chunk.getX() && getZ() == chunk.getZ();
        }
    }
    
    @Override
    public int hashCode() {
        return 31 * x + z;
    }

}
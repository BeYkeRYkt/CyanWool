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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChunkCoords other = (ChunkCoords) obj;
        if (x != other.x)
            return false;
        if (z != other.z)
            return false;
        return true;
    }
}
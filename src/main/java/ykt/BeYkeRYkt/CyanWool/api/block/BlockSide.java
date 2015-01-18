package ykt.BeYkeRYkt.CyanWool.api.block;

/**
 * 
 * From: http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-
 * modding-tutorials/1571226-coding-forge-how-to-properly-confuse-minecraft
 * 
 * @author Two
 */
public enum BlockSide {

    bottom(0, -1, 0), top(0, 1, 0), north(0, 0, -1), south(0, 0, 1), west(-1, 0, 0), east(1, 0, 0);

    private int x;
    private int y;
    private int z;

    BlockSide(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    /**
     * Returns the "native" block orientation based on side. This is a
     * convenient function for blocks that do not rotate (with a metadata of 0).
     *
     * @param side
     *            the side that is searched for.
     * @return the "native" block orientation based on side.
     */
    public static BlockSide getSide(final int side) {
        switch (side) {
            case 0:
                return bottom;
            case 1:
                return top;
            case 2:
                return north;
            case 3:
                return south;
            case 4:
                return west;
            case 5:
                return east;
        }
        // return getRotatedSide(side, 0);
        return null;
    }

}
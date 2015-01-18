package ykt.BeYkeRYkt.CyanWool.api.world;

public enum Dimension {
    NETHER(-1), OVERWORLD(0), END(1);

    private final int id;

    Dimension(int id) {
        this.id = (byte) id;
    }

    public int getId() {
        return this.id;
    }

    public static Dimension getDimension(int i) {
        for (Dimension dimension : values()) {
            if (dimension.id == i) {
                return dimension;
            }
        }
        return null;
    }
}
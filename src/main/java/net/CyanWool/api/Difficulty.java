package net.CyanWool.api;

/**
 * 
 * Source: MinecraftWiki
 * 
 * @author DinDev
 *
 */
public enum Difficulty {
    PEACEFUL(0), EASY(1), NORMAL(2), HARD(3);

    private final int id;

    Difficulty(int id) {
        this.id = (byte) id;
    }

    public int getId() {
        return this.id;
    }

    public static Difficulty getDifficulty(int i) {
        for (Difficulty difficulty : values()) {
            if (difficulty.id == i) {
                return difficulty;
            }
        }
        return Difficulty.NORMAL;
    }
}
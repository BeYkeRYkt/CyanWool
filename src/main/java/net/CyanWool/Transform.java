package net.CyanWool;

import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;

public class Transform {

    public static GameMode transformGameMode(int gamemode) {
        if (gamemode == 1) {
            return GameMode.CREATIVE;
        } else if (gamemode == 2) {
            return GameMode.ADVENTURE;
        } else if (gamemode == 3) {
            return GameMode.SPECTATOR;
        }
        return GameMode.SURVIVAL;
    }

    public static int transformGameMode(GameMode gamemode) {
        switch (gamemode) {
            case CREATIVE:
                return 1;
            case ADVENTURE:
                return 2;
            case SPECTATOR:
                return 3;
            default:
                return 0;
        }
    }

    public static Difficulty transformDifficulty(Byte value) {
        if (value == 0) {
            return Difficulty.PEACEFUL;
        } else if (value == 1) {
            return Difficulty.EASY;
        } else if (value == 3) {
            return Difficulty.HARD;
        }
        return Difficulty.NORMAL;
    }

}
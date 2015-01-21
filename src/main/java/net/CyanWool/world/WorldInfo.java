package net.CyanWool.world;

import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.opennbt.tag.builtin.ByteTag;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.IntTag;
import org.spacehq.opennbt.tag.builtin.LongTag;
import org.spacehq.opennbt.tag.builtin.StringTag;

public class WorldInfo {

    public String name;
    public GameMode gamemode;
    public boolean isHardcore;
    public int dimension;
    public Difficulty difficulty;
    public WorldType worldtype;
    public boolean mapFeatures;
    
    public int spawnX;
    public int spawnY;
    public int spawnZ;
    public long dayTime;
    public long seed;

    public WorldInfo(CompoundTag tag) {
        CompoundTag data = tag.get("Data");
        StringTag name = data.get("LevelName");
        StringTag generator = data.get("generatorName");
        ByteTag mapFeatures = data.get("MapFeatures");
        ByteTag hardcore = data.get("hardcore");
        IntTag gameMode = data.get("GameType");
        ByteTag difficulty = data.get("Difficulty");
        ByteTag lock = data.get("DifficultyLocked");
        LongTag time = data.get("Time");
        LongTag dayTime = data.get("DayTime");
        IntTag xTag = data.get("SpawnX");
        IntTag yTag = data.get("SpawnY");
        IntTag zTag = data.get("SpawnZ");
        // Borders...

        // end
        ByteTag rain = data.get("raining");
        IntTag rainTime = data.get("rainTime");
        ByteTag thunder = data.get("thundering");
        IntTag thunderTime = data.get("thunderTime");

        // GameRules...
        // end

        // set
        this.name = name.getValue();
        this.worldtype = WorldType.valueOf(generator.getValue());
        this.mapFeatures = mapFeatures.getValue() == 1;
        this.isHardcore = hardcore.getValue() == 1;
        // this.gamemode = gameMode.getValue(); - need fix
        this.gamemode = GameMode.SURVIVAL;
        // this.difficulty = need Fix;

        
        this.spawnX = xTag.getValue();
        this.spawnY = yTag.getValue();
        this.spawnZ = zTag.getValue();
    }
}
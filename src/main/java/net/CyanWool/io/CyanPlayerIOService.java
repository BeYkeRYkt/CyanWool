package net.CyanWool.io;

import java.io.File;
import java.io.IOException;

import net.CyanWool.Transform;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.io.PlayerIOService;

import org.spacehq.opennbt.NBTIO;
import org.spacehq.opennbt.tag.builtin.ByteTag;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.FloatTag;
import org.spacehq.opennbt.tag.builtin.IntTag;
import org.spacehq.opennbt.tag.builtin.ShortTag;

public class CyanPlayerIOService implements PlayerIOService {

    @Override
    public void readPlayer(Player player) {
        CompoundTag tag;
        File file = new File(player.getWorld().getPath() + "/playerdata/" + player.getUniqueId().toString());
        if (file.exists()) {
            try {
                tag = NBTIO.readFile(file);
                IntTag gm = tag.get("playerGameType");
                player.setGamemode(Transform.transformGameMode(gm.getValue()));

                IntTag selectItem = tag.get("SelectedItemSlot");
                player.getInventory().setHeldItemSlot(selectItem.getValue());

                // CompoundTag item = tag.get("SelectedItem"); //TODO

                IntTag x = tag.get("SpawnX");
                player.getLocation().setX(x.getValue());

                IntTag y = tag.get("SpawnY");
                player.getLocation().setX(y.getValue());

                IntTag z = tag.get("SpawnZ");
                player.getLocation().setX(z.getValue());

                // IntTag spawnForced = tag.get("SpawnForced"); // TODO

                ByteTag sleeping = tag.get("Sleeping");
                if (sleeping.getValue() == 1) {
                    player.sleepInBedAt(x.getValue(), y.getValue(), z.getValue());
                }

                ShortTag sleepingTicks = tag.get("SleepTimer");
                player.setSleepingTicks(sleepingTicks.getValue());

                IntTag foodLvl = tag.get("foodLevel");
                player.setFoodLevel(foodLvl.getValue());
                // TODO: foodExhaustionLevel, foodSaturationLevel, foodTickTimer
                // TODO: XpLevel, XpP, XpTotal, XpSeed,
                // TODO: Inventory, EnderItems

                CompoundTag abilities = tag.get("abilities");
                FloatTag walk = abilities.get("walkSpeed");
                player.setWalkSpeed(walk.getValue());

                FloatTag fly = abilities.get("flySpeed");
                player.setFlySpeed(fly.getValue());

                ByteTag mayfly = abilities.get("mayfly");
                if (mayfly.getValue() == 1) {
                    player.setAllowFlying(true);
                }

                ByteTag flying = abilities.get("flying");
                if (flying.getValue() == 1) {
                    player.setFlying(true);
                }

                // TODO: invulnerable

                ByteTag mayBuild = abilities.get("mayBuild");
                if (mayBuild.getValue() == 1) {
                    player.setBuild(true);
                }

                // TODO: instabuild
                // End.

                // player.loadCompoundTag(tag);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void savePlayer(Player player) {
        // CompoundTag tag = player.getCompoundTag();
        CompoundTag tag = new CompoundTag("");
        try {
            // player.saveCompoundTag(tag);
            tag.put(new IntTag("playerGameType", Transform.transformGameMode(player.getGameMode())));
            tag.put(new IntTag("SelectedItemSlot", player.getInventory().getHeldItemSlot()));
            tag.put(new IntTag("SpawnX", player.getLocation().getBlockX()));
            tag.put(new IntTag("SpawnY", player.getLocation().getBlockY()));
            tag.put(new IntTag("SpawnZ", player.getLocation().getBlockZ()));

            byte sleep = 0;
            if (player.isSleeping()) {
                sleep = 1;
            }
            tag.put(new ByteTag("Sleeping", sleep));
            tag.put(new ShortTag("SleepTimer", (short) player.getSleepingTicks()));
            tag.put(new IntTag("foodLevel", player.getFoodLevel()));

            CompoundTag abilities = new CompoundTag("abilities");
            abilities.put(new FloatTag("walkSpeed", player.getWalkSpeed()));
            abilities.put(new FloatTag("flySpeed", player.getFlySpeed()));

            byte mayfly = 0;
            if (player.isAllowFlying()) {
                mayfly = 1;
            }
            abilities.put(new ByteTag("mayfly", mayfly));

            byte flying = 0;
            if (player.isFlying()) {
                flying = 1;
            }
            abilities.put(new ByteTag("flying", flying));

            byte mayBuild = 0;
            if (player.canBuild()) {
                mayBuild = 1;
            }
            abilities.put(new ByteTag("mayBuild", mayBuild));
            tag.put(abilities);

            NBTIO.writeFile(tag, new File(player.getWorld().getPath() + "/playerdata/" + player.getUniqueId().toString()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
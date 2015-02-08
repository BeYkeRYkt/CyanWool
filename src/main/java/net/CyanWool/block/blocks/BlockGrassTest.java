package net.CyanWool.block.blocks;

import net.CyanWool.api.ChatColors;
import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockGrassTest extends BlockType {

    public BlockGrassTest() {
        super(2, 228);
        SoundInfo dig = new SoundInfo(MagicValues.value(String.class, GenericSound.COW_HURT), 1.0F, 1.0F);
        SoundInfo step = new SoundInfo(MagicValues.value(String.class, GenericSound.COW_STEP), 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = new ItemStack(3);// dirt id
        ItemStack diamond = new ItemStack(264);
        getDrop().add(item);
        getDrop().add(diamond);
    }

    @Override
    public void onBlockPlace(Location location) {
        ItemStack item = new ItemStack(getID(), getData());
        location.getWorld().dropItemStack(location, item);
    }

    @Override
    public void onBlockDestroy(Location location) {
        location.getWorld().createExplosion(location, 15, true);
    }

    @Override
    public void onBlockLeftInteract(Player player) {
        player.sendMessage("AZAZAZAZAZA");
        player.damage(1);
    }

    @Override
    public void onBlockRightInteract(Player player) {
        player.sendMessage("AZAZAZAZAZAZA-2");
        player.addHealth(1);
    }

    @Override
    public void onEntityWalking(Entity entity) {
        entity.setSneaking(true);
    }

    @Override
    public void onPlayerBreaking(Player player) {
        player.sendMessage(ChatColors.AQUA + "Don't touch me! >:(");
        player.damage(1);
    }
}
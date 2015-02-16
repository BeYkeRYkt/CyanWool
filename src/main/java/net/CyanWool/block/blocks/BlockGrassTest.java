package net.CyanWool.block.blocks;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.basics.MovementComponent;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.utils.ChatColors;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.Sound;

public class BlockGrassTest extends BlockGrass {

    public BlockGrassTest() {
        setData(1);
        SoundInfo dig = new SoundInfo(Sound.COW_HURT, 1.0F, 1.0F);
        SoundInfo step = new SoundInfo(Sound.COW_STEP, 1.0F, 1.0F);

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
        
        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
        component.setSpeed(0.2F); // TEST
    }

    @Override
    public void onBlockRightInteract(Player player) {
        player.sendMessage("AZAZAZAZAZAZA-2");
        player.addHealth(1);
        
        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
        component.setSpeed(0.5F); // TEST
    }

    @Override
    public void onEntityWalking(Entity entity) {
        //Start using ComponentSystem
        MovementComponent component = (MovementComponent) entity.getComponentManager().getComponent("movement");
        component.setSneaking(true);
    }

    @Override
    public void onPlayerBreaking(Player player) {
        player.sendMessage(ChatColors.AQUA + "Don't touch me! >:(");
        player.damage(1);
    }
}
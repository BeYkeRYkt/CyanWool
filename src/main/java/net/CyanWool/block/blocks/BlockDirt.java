package net.CyanWool.block.blocks;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemStack;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockDirt extends BlockType {

    public BlockDirt() {
        super(3, 0, 2);
        SoundInfo dig = new SoundInfo(GenericSound.DIG_GRAVEL, 1.0F, 1.0F);
        SoundInfo step = new SoundInfo(GenericSound.GRAVEL_STEP, 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = new ItemStack(getID());
        getDrop().add(item);
    }
}
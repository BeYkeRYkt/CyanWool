package net.CyanWool.block.blocks;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemStack;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockBedrock extends BlockType {

    public BlockBedrock() {
        super(3, 0);
        SoundInfo dig = new SoundInfo(MagicValues.value(String.class, GenericSound.DIG_STONE), 1.0F, 1.0F);
        SoundInfo step = new SoundInfo(MagicValues.value(String.class, GenericSound.STONE_STEP), 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = new ItemStack(getID());
        getDrop().add(item);
    }

}
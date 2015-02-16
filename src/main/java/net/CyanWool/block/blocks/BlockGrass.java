package net.CyanWool.block.blocks;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Sound;

public class BlockGrass extends BlockType {

    public BlockGrass() {
        super(2, 0, 1);
        SoundInfo dig = new SoundInfo(Sound.DIG_GRASS, 1.0F, 1.0F);
        SoundInfo step = new SoundInfo(Sound.GRASS_STEP, 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = new ItemStack(3);// dirt id
        getDrop().add(item);
    }
}
package net.CyanWool.block.blocks;

import net.CyanWool.api.Register;
import net.CyanWool.api.block.BlockSound;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.block.CyanBlockType;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockGrass extends CyanBlockType {

    public BlockGrass() {
        super(2, 0);
        BlockSound dig = new BlockSound(MagicValues.value(String.class, GenericSound.DIG_GRASS), 1.0F, 1.0F);
        BlockSound step = new BlockSound(MagicValues.value(String.class, GenericSound.GRASS_STEP), 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = Register.getItemStack(3);// dirt id
        getDrop().add(item);
    }

}
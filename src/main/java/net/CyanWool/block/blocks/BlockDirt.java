package net.CyanWool.block.blocks;

import net.CyanWool.api.Register;
import net.CyanWool.api.block.BlockSound;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.block.CyanBlockType;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockDirt extends CyanBlockType {

    public BlockDirt() {
        super(3, 0);
        BlockSound dig = new BlockSound(MagicValues.value(String.class, GenericSound.DIG_GRAVEL), 1.0F, 1.0F);
        BlockSound step = new BlockSound(MagicValues.value(String.class, GenericSound.GRAVEL_STEP), 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);

        ItemStack item = Register.getItemStack(getID());
        getDrop().add(item);
    }

}
package net.CyanWool.block.blocks;

import net.CyanWool.api.Register;
import net.CyanWool.api.block.BlockSound;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.block.CyanBlockType;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;

public class BlockGrass extends CyanBlockType {

    public BlockGrass() {
        super(2, 0);
        BlockSound dig = new BlockSound(GenericSound.DIG_GRASS, 1.0F, 1.0F);
        BlockSound step = new BlockSound(GenericSound.GRASS_STEP, 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);
        setParticle(ParticleEffect.BREAK_BLOCK);// ?

        ItemStack item = Register.getItemStack(3);// dirt id
        getDrop().add(item);
    }

}
package net.devwool.cyanwool.api.item;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.utils.BlockSide;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

public interface ItemType {

    public String getStringId();

    public int getId();

    public void setMaxStackSize(int max);

    public int getMaxStackSize();

    public int getMetadata();

    public void setMetadata(int metadata);

    public int getMaxDurability();

    public void setMaxDurability(int durability);

    public boolean isDamageable();

    public boolean hasSubtypes();

    public void setSubtypes(boolean flag);

    public boolean canHarvestBlock(Block blockIn);

    public boolean canItemInteractionForEntity(ItemStack stack, Player playerIn, EntityLivingBase target);

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected);

    public Action getItemUseAction(ItemStack item);

    public int getMaxItemUseDuration(ItemStack stack);

    // Locale
    public void setUnlocalizedName(String unlocalizedName);

    public String getUnlocalizedName();

    // Events
    public boolean onItemUse(ItemStack stack, Player player, World world, Position pos, BlockSide side);

    public boolean onItemRightClick(ItemStack itemStackIn, World worldIn, Player playerIn);

    public boolean onItemUseFinish(ItemStack stack, World worldIn, Player playerIn);

    public boolean onHitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker);

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, Position pos, EntityLivingBase playerIn);

    public void onCreatedItem(ItemStack stack, World worldIn, Player playerIn);

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, Player playerIn, int timeLeft);

    public void getLocaleDisplayName();

}
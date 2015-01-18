package ykt.BeYkeRYkt.CyanWool.api.entity;

import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;

public interface EntityLivingBase extends Entity {

    public boolean isSwingInProgress();

    public boolean isChild();

    @Override
    public boolean isAlive();

    public boolean isEntityUndead();

    public int getAge();

    public float getAIMoveSpeed();

    public EntityLivingBase getAITarget();

    public int getArrowCountInEntity();

    @Override
    public float getEyeHeight();

    public ItemStack getEquipmentInSlot(int slot);

    public float getHealth();

    public ItemStack getItemInHand();

    public float getMaxHealth();

    public int getTotalArmorValue();

    public void setAIMoveSpeed(float f);

    public void setCurrentItemOrArmor(int slot, ItemStack item);

    public void setArrowCountInEntity(int i);

    public void setHealth(float health);

    public void setJumping(boolean flag);

    public void setRevengeTarget(EntityLivingBase entity);

    @Override
    public void setSprinting(boolean flag);

    public boolean canAttackWithItem();

    public void damage(double amount);

    public void damage(double amount, Entity damaged);
}
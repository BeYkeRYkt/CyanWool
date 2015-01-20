package net.CyanWool.api.entity;

import net.CyanWool.api.inventory.ItemStack;

public interface EntityLivingBase extends Entity {

    public boolean isSwingInProgress();

    public boolean isChild();

    public boolean isEntityUndead();

    public int getAge();

    public EntityLivingBase getTarget();

    public int getArrowCountInEntity();

    @Override
    public float getEyeHeight();

    public ItemStack getEquipmentInSlot(int slot);

    public float getHealth();

    public ItemStack getItemInHand();

    public float getMaxHealth();

    public int getTotalArmorValue();
    
    public boolean isJumping();

    public void setCurrentItemOrArmor(int slot, ItemStack item);

    public void setArrowCountInEntity(int i);

    public void setHealth(float health);

    public void setJumping(boolean flag);

    public void setTarget(EntityLivingBase entity);

    @Override
    public void setSprinting(boolean flag);
    
    public void setMaxHealth(float health);

    public boolean canAttackWithItem();

    public void damage(double amount);

    public void damage(double amount, Entity damaged);
    
    public String getDisplayName();

    public void setDisplayName(String name);
}
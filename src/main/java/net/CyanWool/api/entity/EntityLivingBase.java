package net.CyanWool.api.entity;

import net.CyanWool.api.entity.ai.EntityAITasks;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.inventories.EntityInventory;

public interface EntityLivingBase extends Entity {

    public boolean isChild();

    public boolean isEntityUndead();

    public int getAge();

    public EntityLivingBase getTarget();

    @Override
    public float getEyeHeight();

    public float getHealth();

    public ItemStack getItemInHand();

    public float getMaxHealth();

    public boolean isJumping();

    public void setHealth(float health);

    public void setJumping(boolean flag);

    public void setTarget(EntityLivingBase entity);

    public void setMaxHealth(float health);

    public boolean canAttackWithItem();

    public void damage(double amount);

    public void damage(double amount, Entity damaged);

    public String getDisplayName();

    public void setDisplayName(String name);

    public EntityInventory getInventory();

    public EntityAITasks getTargetAITasks();

    public EntityAITasks getAITasks();
}
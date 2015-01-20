package net.CyanWool.api.entity;

import net.CyanWool.api.inventory.ItemStack;

import org.spacehq.mc.auth.GameProfile;

public interface Human extends EntityLivingBase {

    public GameProfile getGameProfile();
    
    public String getName();

    public boolean hasItemInHand();

    @Override
    public ItemStack getItemInHand(); // getCurrentEquippedItem()

    public void setItemInHand(ItemStack item);

    public boolean isSleeping();

    public boolean isBlocking();

    public void sleepInBedAt(int x, int y, int z);
    
    public void wakeUp();

    public boolean isNeedFood();

    public int getFoodLevel();

    public void setFoodLevel(int level);

    public void closeInventory();

    public int getXPLevel();

    public int getXPTotal();

    public float getXPInBar();

    public void setXPLevel(int level);

    public void setXPTotal(int xp);

    public void setXPInBar(float xp);

    public boolean isDisableDamage();

    public void setDisableDamage(boolean flag);

    public boolean isFlying();

    public void setFlying(boolean flag);

    public boolean isAllowFlying();

    public void setAllowFlying(boolean flag);

    public boolean isCreativeMode();

    public float getFlySpeed();

    public void setFlySpeed(float speed);

    public float getWalkSpeed();

    public void setWalkSpeed(float speed);

    public boolean canBuild();

    public void setBuild(boolean flag);
}
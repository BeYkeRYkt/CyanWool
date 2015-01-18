package ykt.BeYkeRYkt.CyanWool.api.entity;

import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;

public interface Human extends EntityLivingBase {

    public String getDisplayName();

    public void setDisplayName(String name);

    @Override
    public boolean isSneaking();

    @Override
    public void setSneaking(boolean sneak);

    @Override
    public boolean isSprinting();

    @Override
    public void setSprinting(boolean sprinting);

    public void updateInventory();

    public String getName();

    public boolean hasItemInHand();

    @Override
    public ItemStack getItemInHand(); // getCurrentEquippedItem()

    public void setItemInHand(ItemStack item);

    public boolean isSleeping();

    public boolean isBlocking();

    public void sleepInBedAt(int x, int y, int z);

    public float getArmorVisibility();

    public ItemStack getCurrentArmor(int slot);

    public boolean isNeedFood();

    public int getFoodLevel();

    public void setFoodLevel(int level);

    public void closeInventory();

    public int getXPLevel();

    public int getXPTotal();

    public float getXPInBar();

    public int getXPBarCap();

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
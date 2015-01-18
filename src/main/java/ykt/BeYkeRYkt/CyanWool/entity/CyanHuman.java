package ykt.BeYkeRYkt.CyanWool.entity;

import ykt.BeYkeRYkt.CyanWool.api.entity.Human;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;

public class CyanHuman extends CyanEntityLivingBase implements Human {

    public CyanHuman(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getDisplayName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDisplayName(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasItemInHand() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setItemInHand(ItemStack item) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSleeping() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBlocking() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void sleepInBedAt(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getArmorVisibility() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getCurrentArmor(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isNeedFood() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getFoodLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFoodLevel(int level) {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getXPLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getXPTotal() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getXPInBar() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getXPBarCap() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setXPLevel(int level) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setXPTotal(int xp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setXPInBar(float xp) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDisableDamage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setDisableDamage(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isFlying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFlying(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAllowFlying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAllowFlying(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isCreativeMode() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public float getFlySpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFlySpeed(float speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getWalkSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWalkSpeed(float speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canBuild() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setBuild(boolean flag) {
        // TODO Auto-generated method stub

    }

}
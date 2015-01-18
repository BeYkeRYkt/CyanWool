package ykt.BeYkeRYkt.CyanWool.entity;

import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.entity.EntityLivingBase;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;

public class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    public CyanEntityLivingBase(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isSwingInProgress() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChild() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEntityUndead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getAge() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getAIMoveSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public EntityLivingBase getAITarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getArrowCountInEntity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getEquipmentInSlot(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getItemInHand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getMaxHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalArmorValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setAIMoveSpeed(float f) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setCurrentItemOrArmor(int slot, ItemStack item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setArrowCountInEntity(int i) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHealth(float health) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setJumping(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRevengeTarget(EntityLivingBase entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canAttackWithItem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void damage(double amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void damage(double amount, Entity damaged) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTick() {

    }
}
package net.CyanWool.entity;

import org.spacehq.mc.protocol.data.game.values.entity.MobType;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.inventory.ItemStack;

public class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    private int age;
    private int arrowCount;
    private float health;
    private float maxHealth;
    private boolean jump;
    private EntityLivingBase target;
    private String name;
    
    public CyanEntityLivingBase(MobType type) {
        super(type);
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
        return age;
    }

    @Override
    public EntityLivingBase getTarget() {
        return target;
    }

    @Override
    public int getArrowCountInEntity() {
        return arrowCount;
    }

    @Override
    public ItemStack getEquipmentInSlot(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public ItemStack getItemInHand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getTotalArmorValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCurrentItemOrArmor(int slot, ItemStack item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setArrowCountInEntity(int i) {
        this.arrowCount = i;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void setJumping(boolean flag) {
        this.jump = flag;
    }

    @Override
    public void setTarget(EntityLivingBase entity) {
        this.target = entity;
    }

    @Override
    public boolean canAttackWithItem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void damage(double amount) {
        this.damage(amount, null);
    }

    @Override
    public void damage(double amount, Entity damaged) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTick() {
        age++;
        //TODO
    }

    @Override
    public boolean isJumping() {
        return jump;
    }

    @Override
    public void setMaxHealth(float health) {
        this.maxHealth = health;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public void setDisplayName(String name) {
        this.name = name;
    }
}
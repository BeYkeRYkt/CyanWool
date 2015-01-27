package net.CyanWool.entity;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.ai.EntityAITasks;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.inventories.EntityInventory;
import net.CyanWool.api.world.Location;

public class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    private int age;
    private float health;
    private float maxHealth;
    private boolean jump;
    private EntityLivingBase target;
    private String name;
    private EntityInventory inventory;

    // AI
    private EntityAITasks targetTasks;
    private EntityAITasks tasks;

    public CyanEntityLivingBase(Location location) {
        super(location);
        // this.inventory =
        this.targetTasks = new EntityAITasks();
        this.tasks = new EntityAITasks();
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
        getTargetAITasks().onUpdateAI();
        getAITasks().onUpdateAI();
        // TODO
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

    @Override
    public EntityAITasks getTargetAITasks() {
        return targetTasks;
    }

    @Override
    public EntityAITasks getAITasks() {
        return tasks;
    }

    @Override
    public EntityInventory getInventory() {
        return inventory;
    }
}
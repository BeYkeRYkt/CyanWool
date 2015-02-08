package net.CyanWool.entity;

import net.CyanWool.api.SoundInfo;
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
    private SoundInfo damageSound;
    private SoundInfo deathSound;
    private SoundInfo talkSound;

    // AI
    private EntityAITasks targetTasks;
    private EntityAITasks tasks;
    private boolean ai;

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
        setHealth((float) (getHealth() - amount));
    }

    @Override
    public void onTick() {
        super.onTick();
        age++;
        
        if(isAIEnabled()){
        getTargetAITasks().onUpdateAI();
        getAITasks().onUpdateAI();
        }
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

    @Override
    public void addHealth(int i) {
        setHealth(getHealth() + i);
    }

    @Override
    public boolean isAIEnabled() {
        return ai;
    }

    @Override
    public void setAIEnabled(boolean enable) {
        this.ai = enable;
    }


    @Override
    public void onDeath() {  
    }

    @Override
    public void onWalking() {  
    }

    @Override
    public void onDamageEntity(Entity damager, float damage) {
        damage(damage);
    }

    @Override
    public void onAttackEntity(Entity target, float damage) {
    }

    @Override
    public SoundInfo getDamageSound() {
        return damageSound;
    }

    @Override
    public SoundInfo getTalkSound() {
        return talkSound;
    }

    @Override
    public SoundInfo getDeathSound() {
        return deathSound;
    }

    @Override
    public void setDeathSound(SoundInfo sound) {
        this.deathSound = sound;
    }

    @Override
    public void setDamageSound(SoundInfo sound) {
        this.damageSound = sound;
    }

    @Override
    public void setTalkSound(SoundInfo sound) {
        this.talkSound = sound;
    }

    @Override
    public boolean playDamageSound() {
        if(getDamageSound() != null){
            getWorld().playSound(getLocation(), getDamageSound().getSound(), getDamageSound().getVolume(), getDamageSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playTalkSound() {
        if(getTalkSound() != null){
            getWorld().playSound(getLocation(), getTalkSound().getSound(), getTalkSound().getVolume(), getTalkSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playDeathSound() {
        if(getDeathSound() != null){
            getWorld().playSound(getLocation(), getDeathSound().getSound(), getDeathSound().getVolume(), getDeathSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(EntityLivingBase interacter) {
        return false;
    }
    
    @Override
    public float getEyeHeight() {
        return 0;
    }
}
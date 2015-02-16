package net.CyanWool.entity;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.component.basics.AIComponent;
import net.CyanWool.api.entity.component.basics.AgeComponent;
import net.CyanWool.api.entity.component.basics.DisplayNameComponent;
import net.CyanWool.api.entity.component.basics.HealthComponent;
import net.CyanWool.api.entity.component.basics.TransportComponent;
import net.CyanWool.api.world.Location;

public class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    private SoundInfo damageSound;
    private SoundInfo deathSound;
    private SoundInfo talkSound;

    public CyanEntityLivingBase(Location location) {
        super(location);
        getComponentManager().addComponent(new AgeComponent(this));
        getComponentManager().addComponent(new DisplayNameComponent(this));
        getComponentManager().addComponent(new AIComponent(this));
        getComponentManager().addComponent(new TransportComponent(this));
        //getComponentManager().addComponent(new HealthComponent(this, 20)); - for mobs
        //getComponentManager().addComponent(new InventoryComponent(this, inv)); - 
        // this.inventory =
        initMetadata();
    }
    
    @Override
    protected void initMetadata() {
        //super.initMetadata();
        getMetadata().setMetadata(2, "");//Display name
        getMetadata().setMetadata(3, (byte) 0);//Render name tag
        getMetadata().setMetadata(6, (float) 20);//health
        getMetadata().setMetadata(7, 0);//potion color
        getMetadata().setMetadata(8, (byte) 0);//Is Potion Effect Ambient
        getMetadata().setMetadata(9, 0);//Number of Arrows in Entity
        getMetadata().setMetadata(15, (byte) 0);//No ai
    }

    @Override
    public boolean isEntityUndead() {
        // TODO Auto-generated method stub
        return false;
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
        //setHealth((float) (getHealth() - amount));
        if(getComponentManager().hasComponent("health")){
            HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
            component.setHealth(component.getHealth() - amount);
            playDamageSound();
        }
        
    }

    @Override
    public synchronized void onTick() {
        super.onTick();
        //age++;
        
        //if(isAIEnabled()){
        //getTargetAITasks().onUpdateAI();
        //getAITasks().onUpdateAI();
        //}
        //To components
        
        // TODO
    }


    @Override
    public void addHealth(int i) {
        if(getComponentManager().hasComponent("health")){
            HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
            component.setHealth(component.getHealth() + i);
        }
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
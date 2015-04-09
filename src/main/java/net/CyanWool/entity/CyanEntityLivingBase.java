package net.CyanWool.entity;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.component.AIComponent;
import net.CyanWool.api.entity.component.AgeComponent;
import net.CyanWool.api.entity.component.DisplayNameComponent;
import net.CyanWool.api.entity.component.HealthComponent;
import net.CyanWool.api.entity.component.MovementComponent;
import net.CyanWool.api.potions.PotionEffect;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public abstract class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    private SoundInfo damageSound;
    private SoundInfo deathSound;
    private SoundInfo talkSound;
    private List<PotionEffect> potions;

    public CyanEntityLivingBase(Location location) {
        super(location);
        this.potions = new CopyOnWriteArrayList<PotionEffect>();
        getComponentManager().addComponent(new AgeComponent(this));
        getComponentManager().addComponent(new DisplayNameComponent(this));
        getComponentManager().addComponent(new AIComponent(this));
        getComponentManager().addComponent(new HealthComponent(this, 20));// for
                                                                          // others
                                                                          // mobs
                                                                          // -
                                                                          // change
                                                                          // health
        // getComponentManager().addComponent(new InventoryComponent(this,
        // inv));
        // this.inventory =
        initMetadata();
    }

    @Override
    protected void initMetadata() {
        // super.initMetadata();
        getMetadata().setMetadata(2, "");// Display name
        getMetadata().setMetadata(3, (byte) 0);// Render name tag
        getMetadata().setMetadata(6, (float) 20);// health
        getMetadata().setMetadata(7, 0);// potion color
        getMetadata().setMetadata(8, (byte) 0);// Is Potion Effect Ambient
        getMetadata().setMetadata(9, 0);// Number of Arrows in Entity
        getMetadata().setMetadata(15, (byte) 0);// No ai
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
    public void damage(float amount) {
        this.damage(amount, null);
    }

    @Override
    public void damage(float amount, Entity damaged) {
        setHealth(getHealth() - amount);
        playDamageSound();
    }

    @Override
    public void onTick() {
        super.onTick();
        // age++;

        // if(isAIEnabled()){
        // getTargetAITasks().onUpdateAI();
        // getAITasks().onUpdateAI();
        // }
        // To components

        // TODO
    }

    @Override
    public void addHealth(int i) {
        setHealth(getHealth() + i);
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
        if (getDamageSound() != null) {
            getWorld().playSound(getLocation(), getDamageSound().getSound(), getDamageSound().getVolume(), getDamageSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playTalkSound() {
        if (getTalkSound() != null) {
            getWorld().playSound(getLocation(), getTalkSound().getSound(), getTalkSound().getVolume(), getTalkSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playDeathSound() {
        if (getDeathSound() != null) {
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

    @Override
    public float getMaxHealth() {
        HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
        return component.getMaxHealth();
    }

    @Override
    public boolean isJumping() {
        return false;
    }

    @Override
    public void setHealth(float health) {
        HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
        component.setHealth(health);
    }

    @Override
    public float getHealth() {
        HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
        return component.getHealth();
    }

    @Override
    public void setJumping(boolean flag) {
        MovementComponent component = (MovementComponent) getComponentManager().getComponent("movement");
        component.setJumping(flag);
    }

    @Override
    public void setTarget(EntityLivingBase entity) {
        if (getComponentManager().hasComponent("ai")) {
            AIComponent component = (AIComponent) getComponentManager().getComponent("ai");
            component.setTarget(entity);
        }
    }

    @Override
    public void setMaxHealth(float health) {
        HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
        component.setMaxHealth(health);
    }

    @Override
    public EntityLivingBase getTarget() {
        if (getComponentManager().hasComponent("ai")) {
            AIComponent component = (AIComponent) getComponentManager().getComponent("ai");
            return component.getTarget();
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
        return component.getDisplayName();
    }

    @Override
    public void setDisplayName(String name) {
        DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
        component.setDisplayName(name);
    }

    @Override
    public boolean hasDisplayName() {
        DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
        return component.hasDisplayName();
    }

    @Override
    public boolean isRenderDisplayName() {
        DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
        return component.isRenderDisplayName();
    }

    @Override
    public void setRenderDisplayName(boolean flag) {
        DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
        component.setRenderDisplayName(flag);
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return potions;
    }

    @Override
    public void addPotionEffect(PotionEffect potion) {
        potions.add(potion);
    }

    @Override
    public void removePotionEffect(Effect effect) {
        Iterator<PotionEffect> i = getPotionEffects().iterator();
        while (i.hasNext()) {
            PotionEffect pe = i.next();
            if (pe.getPotionType().getEffect() == effect) {
                i.remove();
                break;
            }
        }
    }
}
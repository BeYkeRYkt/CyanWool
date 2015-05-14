package net.devwool.cyanwool.api.entity;

import net.devwool.cyanwool.api.world.Position;

import org.spacehq.mc.protocol.data.game.values.world.Sound;

public interface EntityLivingBase extends Entity {

    public boolean isEntityUndead();

    public float getEyeHeight();

    public Position getEyePosition();

    public void damage(float amount);

    public void damage(float amount, Entity damager);

    public double getHealth();

    public void setHealth(double health);

    public double getMaxHealth();

    public void settMaxHealth(double maxHealth);

    public void setTarget(EntityLivingBase entity);

    public EntityLivingBase getTarget();

    public Sound getDamageSound();

    public Sound getTalkSound();

    public Sound getDeathSound();

    public void setDeathSound(Sound sound);

    public void setDamageSound(Sound sound);

    public void setTalkSound(Sound sound);

    public boolean playDamageSound();

    public boolean playTalkSound();

    public boolean playDeathSound();

    public String getDisplayName();

    public void setDisplayName(String name);

    public boolean hasDisplayName();

    public boolean isRenderDisplayName();

    public void setRenderDisplayName(boolean flag);

    public void interact(EntityLivingBase entity);

    // Events...
    public void onDamageEntity(Entity damager, float damage);

    public void onAttackEntity(Entity target, float damage);

    public void onDeathEntity();

    public void onWalkingEntity();

    public void onInteractEntity(EntityLivingBase interacter);
}
package net.CyanWool.api.entity;

import net.CyanWool.api.SoundInfo;

public interface EntityLivingBase extends Entity {

    public boolean isEntityUndead();

    public float getEyeHeight();

    public boolean canAttackWithItem();

    public void damage(double amount);

    public void damage(double amount, Entity damaged);

    public void addHealth(int i);

    public void onDamageEntity(Entity damager, float damage);

    public void onAttackEntity(Entity target, float damage);

    public void onDeath();

    public void onWalking();

    public boolean interact(EntityLivingBase interacter);

    public SoundInfo getDamageSound();

    public SoundInfo getTalkSound();

    public SoundInfo getDeathSound();

    public void setDeathSound(SoundInfo sound);

    public void setDamageSound(SoundInfo sound);

    public void setTalkSound(SoundInfo sound);

    public boolean playDamageSound();

    public boolean playTalkSound();

    public boolean playDeathSound();
}
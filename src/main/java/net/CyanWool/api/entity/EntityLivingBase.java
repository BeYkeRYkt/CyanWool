package net.CyanWool.api.entity;

import java.util.List;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.potions.PotionEffect;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public interface EntityLivingBase extends Entity {

    public boolean isEntityUndead();

    public float getEyeHeight();

    public boolean canAttackWithItem();

    public void damage(float amount);

    public void damage(float amount, Entity damaged);

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

    // for systems components
    public float getMaxHealth();

    public boolean isJumping();

    public void setHealth(float health);

    public float getHealth();

    public void setJumping(boolean flag);

    public void setTarget(EntityLivingBase entity);

    public void setMaxHealth(float health);

    public EntityLivingBase getTarget();

    public String getDisplayName();

    public void setDisplayName(String name);

    public boolean hasDisplayName();

    public boolean isRenderDisplayName();

    public void setRenderDisplayName(boolean flag);

    public List<PotionEffect> getPotionEffects();

    public void addPotionEffect(PotionEffect potion);

    public void removePotionEffect(Effect effect);
}
package net.CyanWool.api.potions;

import net.CyanWool.api.entity.EntityLivingBase;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public class PotionType {

    private Effect effect;
    private int level;
    private int duration;

    public PotionType(Effect effect, int level, int duration) {
        this.effect = effect;
        this.level = level;
        this.duration = duration;
    }

    public void onTick(EntityLivingBase entity) {
    }

    public Effect getEffect() {
        return effect;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((effect == null) ? 0 : effect.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PotionType)) {
            return false;
        }
        PotionType other = (PotionType) obj;
        if (effect != other.effect) {
            return false;
        }
        return true;
    }
}
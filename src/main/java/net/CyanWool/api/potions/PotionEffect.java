package net.CyanWool.api.potions;

import net.CyanWool.api.entity.EntityLivingBase;

public class PotionEffect {

    private PotionType type;
    private int delay;
    private int ticks;

    public PotionEffect(PotionType type, int delay) {
        this.type = type;
        this.delay = delay;
        this.ticks = delay;
    }

    public PotionType getPotionType() {
        return type;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int ticks) {
        this.delay = ticks;
        this.ticks = ticks;
    }

    public void onTick(EntityLivingBase entity) {
        ticks--;
        if (ticks == 0) {
            ticks = getDelay();
            getPotionType().onTick(entity);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + delay;
        result = prime * result + ticks;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        if (!(obj instanceof PotionEffect)) {
            return false;
        }
        PotionEffect other = (PotionEffect) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
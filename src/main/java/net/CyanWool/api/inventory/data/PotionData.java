package net.CyanWool.api.inventory.data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.CyanWool.api.inventory.ItemData;
import net.CyanWool.api.potions.PotionEffect;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public class PotionData extends ItemData {

    private List<PotionEffect> effects;

    public PotionData(ItemData data) {
        this(data.getDurability());
    }

    public PotionData(short maxDurability) {
        super(maxDurability);
        this.effects = new CopyOnWriteArrayList<PotionEffect>();
    }

    public void addPotionEffect(PotionEffect effect) {
        this.effects.add(effect);
    }

    public List<PotionEffect> getPotionEffects() {
        return effects;
    }

    public void removePotionEffect(Effect effect) {
        for (PotionEffect i : getPotionEffects()) {
            if (i.getPotionType().getEffect() == effect) {
                getPotionEffects().remove(i);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((effects == null) ? 0 : effects.hashCode());
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
        if (!(obj instanceof PotionData)) {
            return false;
        }
        PotionData other = (PotionData) obj;
        if (effects == null) {
            if (other.effects != null) {
                return false;
            }
        } else if (!effects.equals(other.effects)) {
            return false;
        }
        return true;
    }

}
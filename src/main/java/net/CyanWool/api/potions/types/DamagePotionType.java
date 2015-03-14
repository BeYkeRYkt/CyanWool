package net.CyanWool.api.potions.types;

import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.potions.PotionType;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public class DamagePotionType extends PotionType {

    private int damage;

    public DamagePotionType(int level, int duration) {
        super(Effect.DAMAGE, level, duration);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void onTick(EntityLivingBase entity) {
        if (entity.isMonster()) {
            entity.setHealth(entity.getHealth() + damage);
        } else {
            entity.damage(damage);
        }
    }

}
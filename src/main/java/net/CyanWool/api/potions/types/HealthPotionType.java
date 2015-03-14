package net.CyanWool.api.potions.types;

import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.potions.PotionType;

import org.spacehq.mc.protocol.data.game.values.entity.Effect;

public class HealthPotionType extends PotionType {

    private int health;

    public HealthPotionType(int level, int duration) {
        super(Effect.HEAL, level, duration);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void onTick(EntityLivingBase entity) {
        if (!entity.isMonster()) {
            entity.setHealth(entity.getHealth() + health);
        } else {
            entity.damage(health);
        }
    }

}
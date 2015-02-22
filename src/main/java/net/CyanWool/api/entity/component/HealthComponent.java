package net.CyanWool.api.entity.component;

import net.CyanWool.api.entity.Entity;

/**
 * Здоровье Entity
 * 
 * @author DinDev
 */
public class HealthComponent extends SystemComponent {

    private float maxHealth;
    private float health;
    private boolean needUpdate;

    public HealthComponent(Entity entity, float maxHealth) {
        super(entity);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
        if (getHealth() > getMaxHealth()) {
            setHealth(maxHealth);
        }
        this.needUpdate = true;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
        this.needUpdate = true;
    }

    @Override
    public String getID() {
        return "health";
    }

    @Override
    public void update() {
        getEntity().getMetadata().setMetadata(6, this.health = this.maxHealth);
    }

    @Override
    public boolean autoUpdate() {
        return true;
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean b) {
        this.needUpdate = b;
    }

}
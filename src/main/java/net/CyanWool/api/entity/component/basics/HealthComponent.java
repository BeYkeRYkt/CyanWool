package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.Component;

/**
 * Здоровье Entity
 * 
 * @author DinDev
 */
public class HealthComponent extends Component {

    private double maxHealth;
    private double health;

    public HealthComponent(Entity entity, double maxHealth) {
        super(entity);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String getID() {
        return "health";
    }

}
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
    private boolean needUpdate;

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
        this.needUpdate = true;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
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
    
    public boolean isNeedUpdate(){
        return needUpdate;
    }

    public void setNeedUpdate(boolean b) {
        this.needUpdate = b;
    }

}
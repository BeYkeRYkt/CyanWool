package net.CyanWool.api.entity.component;

import net.CyanWool.api.entity.EntityLivingBase;

/**
 * Сытость для игрока
 * 
 * @author DinDev
 *
 */
public class FoodComponent extends SystemComponent {

    private int maxFoodLevel;
    private int foodLevel;
    private double damage;
    private int tickDelayDamage;
    private int ticks;

    public FoodComponent(EntityLivingBase entity, int maxFoodLevel) {
        super(entity);
        this.maxFoodLevel = maxFoodLevel;
        this.foodLevel = maxFoodLevel;
        this.damage = 1;
        this.tickDelayDamage = 20; // One seconds
    }

    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
        this.foodLevel = maxFoodLevel;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getTickDelayDamage() {
        return tickDelayDamage;
    }

    public void setTickDelayDamage(int tickDelayDamage) {
        this.tickDelayDamage = tickDelayDamage;
    }

    @Override
    public void initialization() {
    }

    @Override
    public void update() {
        // Примитивный FoodLevel :/
        if (getFoodLevel() == 0) {
            ticks--;
            if (ticks == 0) {

            }
        }
    }

    @Override
    public boolean autoUpdate() {
        return true;
    }

    @Override
    public String getID() {
        return "food";
    }
}
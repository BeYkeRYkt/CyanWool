package net.CyanWool.api.entity.component;

import net.CyanWool.api.entity.Entity;

/**
 * Главный компонент.
 * 
 * @author DinDev
 *
 */
public abstract class Component { // или interface ?

    private Entity entity;

    public Component(Entity entity) {
        this.entity = entity;
    }

    /**
     * Вызывается для инициализации компонента.
     */
    public void initialization() {
    }

    /**
     * Вызывается для обновления компонента.
     */
    public void update() {
    }

    /**
     * Автоматически обновляется системой ?
     * 
     * @return Да / Нет.
     */
    public boolean autoUpdate() {
        return false;
    }

    /**
     * Entity, к которому прицеплен компонент
     */
    public Entity getEntity() {
        return entity;
    }
    
    public abstract String getID();
}
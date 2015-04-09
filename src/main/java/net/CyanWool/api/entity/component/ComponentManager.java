package net.CyanWool.api.entity.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.CyanWool.api.entity.Entity;

public class ComponentManager {

    private ConcurrentMap<String, Component> components;
    private Entity entity;

    public ComponentManager(Entity entity) {
        this.entity = entity;
        this.components = new ConcurrentHashMap<String, Component>();
    }

    public Entity getEntity() {
        return entity;
    }

    /**
     * @return Список компонентов (Если они вообще есть).
     */
    public List<Component> getComponents() {
        return new ArrayList<Component>(components.values());
    }

    /**
     * Добавляет новый компонент в систему.
     * 
     * @param component
     *            - Класс компонента. Например: new HealthComponent(20);
     * @return Результат добавления.
     */
    public synchronized boolean addComponent(Component component) {
        if (!hasComponent(component.getID())) {
            components.put(component.getID(), component);
            component.initialization();
            return true;
        }
        return false;
    }

    /**
     * Проверка наличия компонента.
     * 
     * @param id
     *            - Ид компонента. Например: "health"
     * @return Результат проверки.
     */
    public synchronized boolean hasComponent(String id) {
        return components.get(id) != null;
    }

    /**
     * Возвращает компонент по иду.
     * 
     * @param id
     *            - Ид компонента. Например: "health"
     * @return Компонент.
     */
    public synchronized Component getComponent(String id) {
        return components.get(id);
    }

    /**
     * Удаляет компонент из базы
     * 
     * @param id
     *            - Ид компонента. Например: "health"
     * @return Результат удаления.
     */
    public synchronized boolean removeComponent(String id) {
        if (hasComponent(id)) {
            if (getComponent(id) instanceof SystemComponent) {
                // IS IMPOSSIBLE!!1!!
                return false;
            }
            components.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Обновление компонентов (Обычно вызывается из Entity)
     */
    public synchronized void onUpdateComponents() {
        for (Component component : components.values()) {
            if (component.autoUpdate()) {
                component.update();
            }
        }
    }
}
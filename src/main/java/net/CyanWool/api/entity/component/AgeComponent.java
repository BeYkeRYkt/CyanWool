package net.CyanWool.api.entity.component;

import net.CyanWool.api.entity.Entity;

public class AgeComponent extends SystemComponent {

    private int age;

    public AgeComponent(Entity entity) {
        super(entity);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChild() {
        // Test
        return age < 100;
    }

    @Override
    public String getID() {
        return "age";
    }

}
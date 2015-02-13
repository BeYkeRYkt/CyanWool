package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.Component;

public class AgeComponent extends Component {

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
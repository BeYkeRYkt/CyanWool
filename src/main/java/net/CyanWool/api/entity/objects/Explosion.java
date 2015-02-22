package net.CyanWool.api.entity.objects;

import net.CyanWool.api.entity.Entity;

public interface Explosion extends Entity {

    public int getPower();

    public void setPower(int power);

    public boolean isFlaming();

    public boolean setFlaming(boolean flag);

    public int getDamage();

    public void setDamage(int damage);

}
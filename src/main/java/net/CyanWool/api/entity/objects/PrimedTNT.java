package net.CyanWool.api.entity.objects;

import net.CyanWool.api.entity.Entity;

public interface PrimedTNT extends Entity {

    public int getPower();

    public void setPower(int power);

    public int getFuseTicks();

    public void setFuseTicks(int ticks);

}
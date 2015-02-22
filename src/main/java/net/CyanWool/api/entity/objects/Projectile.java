package net.CyanWool.api.entity.objects;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;

public interface Projectile extends Entity {

    public EntityLivingBase getShooter();

    public void setShooter(EntityLivingBase source);

    public boolean doesBounce();

    public void setBounce(boolean doesBounce);
}
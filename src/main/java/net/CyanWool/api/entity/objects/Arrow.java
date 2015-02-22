package net.CyanWool.api.entity.objects;

public interface Arrow extends Projectile {

    public int getKnockbackStrength();

    public void setKnockbackStrength(int knockbackStrength);

    public boolean isCritical();

    public void setCritical(boolean critical);
}
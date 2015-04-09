package net.CyanWool.api.entity.monsters;

public interface EntityZombie extends EntityMonster {

    public boolean isBaby();

    public void setBaby(boolean flag);

    public boolean isVillager();

    public void setVillager(boolean flag);
}
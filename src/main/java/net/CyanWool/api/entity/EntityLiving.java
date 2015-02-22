package net.CyanWool.api.entity;

public interface EntityLiving extends EntityLivingBase {

    public boolean isNoDespawnRequired();

    // public EntityLivingBase getAttackTarget();

    // public String getCustomNameTag();

    // public IEntityAITasks getTargetAITasks();

    // public IEntityAITasks getAITasks();

    // public INavigate getNavigator();

    public int getTalkInterval();

    public int setTalkInterval(int interval);

    // public void setAttackTarget(EntityLivingBase base);

    public void setCanPickUpLoot(boolean flag);

    // public void setCustomNameTag(String name);

    public void setEquipmentDropChance(int slot, float chance);

    public boolean canPickUpLoot();
}
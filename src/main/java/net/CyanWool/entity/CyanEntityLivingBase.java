package net.CyanWool.entity;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.component.basics.AIComponent;
import net.CyanWool.api.entity.component.basics.AgeComponent;
import net.CyanWool.api.entity.component.basics.DisplayNameComponent;
import net.CyanWool.api.entity.component.basics.HealthComponent;
import net.CyanWool.api.entity.component.basics.TransportComponent;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.EntityMetadata;
import org.spacehq.mc.protocol.data.game.values.entity.MetadataType;

public class CyanEntityLivingBase extends CyanEntity implements EntityLivingBase {

    private SoundInfo damageSound;
    private SoundInfo deathSound;
    private SoundInfo talkSound;

    public CyanEntityLivingBase(Location location) {
        super(location);
        initMetadata();
        getComponentManager().addComponent(new AgeComponent(this));
        getComponentManager().addComponent(new DisplayNameComponent(this));
        getComponentManager().addComponent(new AIComponent(this));
        getComponentManager().addComponent(new TransportComponent(this));
        //getComponentManager().addComponent(new HealthComponent(this, 20)); - for mobs
        //getComponentManager().addComponent(new InventoryComponent(this, inv)); - 
        // this.inventory =
    }
    
    @Override
    protected void initMetadata() {
        //super.initMetadata();
        metadata[2] = new EntityMetadata(2, MetadataType.STRING, ""); //Display name
        metadata[3] = new EntityMetadata(3, MetadataType.BYTE, 0);//Render name tag
        metadata[4] = new EntityMetadata(6, MetadataType.FLOAT, 0);//health
        metadata[5] = new EntityMetadata(7, MetadataType.INT, 0);//potion color
        metadata[6] = new EntityMetadata(8, MetadataType.BYTE, 0);//Is Potion Effect Ambient
        metadata[7] = new EntityMetadata(9, MetadataType.BYTE, 0);//Number of Arrows in Entity
        metadata[8] = new EntityMetadata(15, MetadataType.BYTE, 0);//No ai
    }

    @Override
    public boolean isEntityUndead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canAttackWithItem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void damage(double amount) {
        this.damage(amount, null);
    }

    @Override
    public void damage(double amount, Entity damaged) {
        //setHealth((float) (getHealth() - amount));
        if(getComponentManager().hasComponent("health")){
            HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
            component.setHealth(component.getHealth() - amount);
        }
        
    }

    @Override
    public void onTick() {
        super.onTick();
        //age++;
        
        //if(isAIEnabled()){
        //getTargetAITasks().onUpdateAI();
        //getAITasks().onUpdateAI();
        //}
        //To components
        
        //TODO: Update metadata
        if(getComponentManager().hasComponent("displayName")){
            DisplayNameComponent component = (DisplayNameComponent) getComponentManager().getComponent("displayName");
            metadata[2] = new EntityMetadata(2, MetadataType.STRING, component.getDisplayName());
            
            byte data = 1;
            if(!component.isRenderDisplayName()){
                data = 0;
            }else{
                data = 1;
            }
            metadata[3] = new EntityMetadata(3, MetadataType.BYTE, data);
        }
        
        if(getComponentManager().hasComponent("health")){
            HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
            metadata[4] = new EntityMetadata(6, MetadataType.FLOAT, component.getHealth());
        }
                
        if(getComponentManager().hasComponent("ai")){
            AIComponent component = (AIComponent) getComponentManager().getComponent("ai");
            byte disable = 1;
            
            if(component.isAIEnabled()){
                disable = 0;
            }else{
                disable = 1;
            }
            
            metadata[8] = new EntityMetadata(15, MetadataType.BYTE, disable);
        }
        
        // TODO
    }


    @Override
    public void addHealth(int i) {
        if(getComponentManager().hasComponent("health")){
            HealthComponent component = (HealthComponent) getComponentManager().getComponent("health");
            component.setHealth(component.getHealth() + i);
        }
    }

    @Override
    public void onDeath() {  
    }

    @Override
    public void onWalking() {  
    }

    @Override
    public void onDamageEntity(Entity damager, float damage) {
        damage(damage);
    }

    @Override
    public void onAttackEntity(Entity target, float damage) {
    }

    @Override
    public SoundInfo getDamageSound() {
        return damageSound;
    }

    @Override
    public SoundInfo getTalkSound() {
        return talkSound;
    }

    @Override
    public SoundInfo getDeathSound() {
        return deathSound;
    }

    @Override
    public void setDeathSound(SoundInfo sound) {
        this.deathSound = sound;
    }

    @Override
    public void setDamageSound(SoundInfo sound) {
        this.damageSound = sound;
    }

    @Override
    public void setTalkSound(SoundInfo sound) {
        this.talkSound = sound;
    }

    @Override
    public boolean playDamageSound() {
        if(getDamageSound() != null){
            getWorld().playSound(getLocation(), getDamageSound().getSound(), getDamageSound().getVolume(), getDamageSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playTalkSound() {
        if(getTalkSound() != null){
            getWorld().playSound(getLocation(), getTalkSound().getSound(), getTalkSound().getVolume(), getTalkSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean playDeathSound() {
        if(getDeathSound() != null){
            getWorld().playSound(getLocation(), getDeathSound().getSound(), getDeathSound().getVolume(), getDeathSound().getPitch());
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(EntityLivingBase interacter) {
        return false;
    }
    
    @Override
    public float getEyeHeight() {
        return 0;
    }
}
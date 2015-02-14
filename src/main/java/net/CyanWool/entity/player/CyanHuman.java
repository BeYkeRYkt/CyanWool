package net.CyanWool.entity.player;

import net.CyanWool.api.Gamemode;
import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.component.basics.FoodComponent;
import net.CyanWool.api.entity.component.basics.HealthComponent;
import net.CyanWool.api.entity.component.basics.InventoryComponent;
import net.CyanWool.api.entity.component.basics.XPComponent;
import net.CyanWool.api.entity.player.Human;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.Sound;
import net.CyanWool.entity.CyanEntityLivingBase;

import org.spacehq.mc.auth.GameProfile;

public class CyanHuman extends CyanEntityLivingBase implements Human {

    private GameProfile profile;
    private boolean sleeping;
    private int sleepingTicks;
    private boolean blocking;

    private float xpInBar;
    private boolean disableDamage;
    private boolean isFlying;
    private boolean isAllowFlying;
    private boolean isCreative;
    private float flySpeed;
    private float walkSpeed;
    private boolean canBuild;
    private Inventory viewInventory;
    private Gamemode gameMode;

    public CyanHuman(GameProfile profile, Location location) {
        super(location);// TODO
        this.profile = profile;
        getComponentManager().removeComponent("ai");
        getComponentManager().addComponent(new HealthComponent(this, 20));
        getComponentManager().addComponent(new FoodComponent(this, 20)); // ???
        getComponentManager().addComponent(new XPComponent(this));
        setDamageSound(new SoundInfo(Sound.PLAYER_HURT, 1.0F, 1.0F));
    }

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public boolean hasItemInHand() {
        if(getComponentManager().hasComponent("inventory")){
            InventoryComponent component = (InventoryComponent) getComponentManager().getComponent("inventory");
            return component.getInventory().getItemInHand() != null;
        }
        return false;
    }

    @Override
    public void setItemInHand(ItemStack item) {
        if(getComponentManager().hasComponent("inventory")){
            InventoryComponent component = (InventoryComponent) getComponentManager().getComponent("inventory");
            component.getInventory().setItemInHand(item);
        }
    }

    @Override
    public boolean isSleeping() {
        return sleeping;
    }

    @Override
    public boolean isBlocking() {
        return blocking;
    }

    @Override
    public void sleepInBedAt(int x, int y, int z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeInventory() {
        if (viewInventory != null) {
            viewInventory.closeInventory(this);
        }
    }

    @Override
    public float getXPInBar() {
        return xpInBar;
    }

    @Override
    public void setXPInBar(float xp) {
        this.xpInBar = xp;
    }

    @Override
    public boolean isDisableDamage() {
        return disableDamage;
    }

    @Override
    public void setDisableDamage(boolean flag) {
        this.disableDamage = flag;
    }

    @Override
    public boolean isFlying() {
        return isFlying;
    }

    @Override
    public void setFlying(boolean flag) {
        this.isFlying = flag;
    }

    @Override
    public boolean isAllowFlying() {
        return isAllowFlying;
    }

    @Override
    public void setAllowFlying(boolean flag) {
        this.isAllowFlying = flag;
    }

    @Override
    public boolean isCreativeMode() {
        return isCreative;
    }

    @Override
    public float getFlySpeed() {
        return flySpeed;
    }

    @Override
    public void setFlySpeed(float speed) {
        this.flySpeed = speed;
    }

    @Override
    public float getWalkSpeed() {
        return walkSpeed;
    }

    @Override
    public void setWalkSpeed(float speed) {
        this.walkSpeed = speed;
    }

    @Override
    public boolean canBuild() {
        return canBuild;
    }

    @Override
    public void setBuild(boolean flag) {
        this.canBuild = flag;
    }

    @Override
    public void wakeUp() {
        this.sleeping = false;
        this.sleepingTicks = 0;
        // todo...
    }

    @Override
    public Inventory getViewInventory() {
        return viewInventory;
    }

    @Override
    public Inventory getEnderChest() {
        return null;// TODO ?
    }

    @Override
    public void openInventory(Inventory inventory) {
        closeInventory();
        inventory.openInventory(this);
        viewInventory = inventory;
    }

    @Override
    public Gamemode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGamemode(Gamemode mode) {
        this.gameMode = mode;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public int getSleepingTicks() {
        return sleepingTicks;
    }

    @Override
    public void setSleepingTicks(int ticks) {
        this.sleepingTicks = ticks;
    }
    
    
    @Override
    public synchronized void onTick(){
        super.onTick();
        if (sleeping) {
            ++sleepingTicks;
        } else {
            sleepingTicks = 0;
        }
    }
    // Not from API

    public GameProfile getGameProfile() {
        return profile;
    }

}
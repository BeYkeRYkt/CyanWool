package net.CyanWool.entity.player;

import net.CyanWool.api.SoundInfo;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.component.FoodComponent;
import net.CyanWool.api.entity.component.HealthComponent;
import net.CyanWool.api.entity.component.XPComponent;
import net.CyanWool.api.entity.player.Human;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.inventories.PlayerInventory;
import net.CyanWool.api.world.Location;
import net.CyanWool.entity.CyanEntityLivingBase;
import net.CyanWool.inventory.inventories.CyanPlayerInventory;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public abstract class CyanHuman extends CyanEntityLivingBase implements Human {

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
    private GameMode gameMode;
    private PlayerInventory inventory;

    public CyanHuman(GameProfile profile, Location location) {
        super(location);// TODO
        this.profile = profile;
        getComponentManager().removeComponent("ai");
        getComponentManager().addComponent(new FoodComponent(this, 20)); // ???
        getComponentManager().addComponent(new XPComponent(this));
        CyanPlayerInventory inv = new CyanPlayerInventory(getName());
        this.inventory = inv;
        setDamageSound(new SoundInfo(GenericSound.PLAYER_HURT, 1.0F, 1.0F));
        ((HealthComponent) getComponentManager().getComponent("health")).setMaxHealth(20);
        ((HealthComponent) getComponentManager().getComponent("health")).setHealth(20);
    }

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public boolean hasItemInHand() {
        return getInventory().getItemInHand() != null;
    }

    @Override
    public void setItemInHand(ItemStack item) {
        getInventory().setItemInHand(item);
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
            viewInventory = null;
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
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGamemode(GameMode mode) {
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
    public void onTick() {
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

    @Override
    public boolean isNeedFood() {
        FoodComponent component = (FoodComponent) getComponentManager().getComponent("food");
        return component.getFoodLevel() < 20;
    }

    @Override
    public int getFoodLevel() {
        FoodComponent component = (FoodComponent) getComponentManager().getComponent("food");
        return component.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int level) {
        FoodComponent component = (FoodComponent) getComponentManager().getComponent("food");
        component.setFoodLevel(level);
    }

    @Override
    public int getXPLevel() {
        XPComponent component = (XPComponent) getComponentManager().getComponent("xp");
        return component.getXPLevel();
    }

    @Override
    public int getXPTotal() {
        XPComponent component = (XPComponent) getComponentManager().getComponent("xp");
        return component.getXPTotal();
    }

    @Override
    public void setXPLevel(int level) {
        XPComponent component = (XPComponent) getComponentManager().getComponent("xp");
        component.setXPLevel(level);
    }

    @Override
    public void setXPTotal(int xp) {
        XPComponent component = (XPComponent) getComponentManager().getComponent("xp");
        component.setXPTotal(xp);
    }

    @Override
    public PlayerInventory getInventory() {
        return inventory;
    }
}
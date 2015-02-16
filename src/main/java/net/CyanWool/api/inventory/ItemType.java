package net.CyanWool.api.inventory;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.player.Player;

public class ItemType {

    private short maxDurability;
    private int maxStack;
    private int id;
    private int data;
    private boolean isDamageable;

    public ItemType(int id, int data, short maxDurability, int maxStackSize, boolean isDamageable) {
        this.id = id;
        this.data = data;
        this.maxDurability = maxDurability;
        this.maxStack = maxStackSize;
        this.isDamageable = isDamageable;
    }

    /**
     * @return the maxDurability
     */
    public short getMaxDurability() {
        return maxDurability;
    }

    /**
     * @param maxDurability
     *            the maxDurability to set
     */
    public void setMaxDurability(short maxDurability) {
        this.maxDurability = maxDurability;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @return the maxStack
     */
    public int getMaxStackSize() {
        return maxStack;
    }

    /**
     * @param maxStack
     *            the maxStack to set
     */
    public void setMaxStackSize(int maxStack) {
        this.maxStack = maxStack;
    }

    public void onItemLeftClick(ItemStack item, Player player) {
    }

    public void onItemDamage(ItemStack item, Player player) {
    }

    public void onItemRightClick(ItemStack item, Player player) {
    }

    public void onHitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase damager) {
    }

    public void onBlockDestroyed(ItemStack item, Block block, EntityLivingBase entity) {
    }

    public boolean hasDestroyBlock(ItemStack item, Block block) {
        return true;
    }

    public void onInventoryUpdate(ItemStack item, Inventory inventory) {
    }

    /**
     * @return the isDamageable
     */
    public boolean isDamageable() {
        return isDamageable;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ItemType)) {
            return false;
        } else {
            ItemType item = (ItemType) other;
            return item.getId() == getId() && item.getData() == getData();
        }
    }
}
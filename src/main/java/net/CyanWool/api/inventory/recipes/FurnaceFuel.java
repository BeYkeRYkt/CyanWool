package net.CyanWool.api.inventory.recipes;

import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.inventory.ItemType;

public class FurnaceFuel {

    private ItemType type;
    private int time;

    public FurnaceFuel(ItemStack item, int time) {
        this.type = item.getType();
        this.time = time;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the type
     */
    public ItemType getItemType() {
        return type;
    }
}
package net.CyanWool.api.inventory;

import net.CyanWool.api.Register;

public class ItemStack {

    private int id;
    private int amount;
    private int data;
    private ItemData itemdata;
    private ItemType type;

    public ItemStack(int id) {
        this(id, 1);
    }

    public ItemStack(int id, int amount) {
        this(id, amount, 0);
    }

    public ItemStack(int id, int amount, int data) {
        this(id, amount, data, Register.getItemType(id, data));
    }

    public ItemStack(int id, int amount, int data, ItemType type) {
        this.id = id;
        this.amount = amount;
        this.data = data;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getData() {
        return this.data;
    }

    public boolean hasItemData() {
        return getItemData() != null;
    }

    /**
     * @return the itemdata
     */
    public ItemData getItemData() {
        return itemdata;
    }

    /**
     * @param itemdata
     *            the itemdata to set
     */
    public void setItemData(ItemData itemdata) {
        this.itemdata = itemdata;
    }

    @Override
    public ItemStack clone() {
        ItemStack clone = new ItemStack(id, amount, data);
        clone.setItemData(getItemData());
        return clone;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ItemStack)) {
            return false;
        } else {
            ItemStack item = (ItemStack) other;
            return item.getId() == getId() && item.getData() == getData(); // Maybe
                                                                           // todo
        }
    }

    /**
     * @return the type
     */
    public ItemType getItemType() {
        return type;
    }
}
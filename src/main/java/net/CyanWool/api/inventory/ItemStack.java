package net.CyanWool.api.inventory;

public class ItemStack {

    private int id;
    private int amount;
    private int data;
    private ItemData itemdata;

    public ItemStack(int id) {
        this(id, 1);
    }

    public ItemStack(int id, int amount) {
        this(id, amount, 0);
    }

    public ItemStack(int id, int amount, int data) {
        this.id = id;
        this.amount = amount;
        this.data = data;
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

    public void saveNbt() {
    }

    public void loadNbt() {
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
        return false;
    }
}
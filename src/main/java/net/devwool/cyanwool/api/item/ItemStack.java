package net.devwool.cyanwool.api.item;

public class ItemStack {

    private ItemType itemType;

    public ItemStack(ItemType type) {
        this.itemType = type;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
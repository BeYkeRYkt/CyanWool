package net.CyanWool.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.CyanWool.api.entity.player.Human;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.InventoryType;
import net.CyanWool.api.inventory.ItemStack;

public class CyanInventory implements Inventory {

    private int size;
    private String name;
    private InventoryType type;
    private Map<Integer, ItemStack> items;
    private List<Human> viewers;

    public CyanInventory(int size, String name, InventoryType type) {
        this.size = size;
        this.name = name;
        this.type = type;
        this.items = new HashMap<Integer, ItemStack>();
        this.viewers = new ArrayList<Human>();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public ItemStack getItemStack(int slot) {
        if (items.containsKey(slot)) {
            return items.get(slot);
        }
        return null;
    }

    @Override
    public void setItemStack(int slot, ItemStack item) {
        items.put(slot, item);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCustomName() {
        return getName() != null;
    }

    @Override
    public boolean isUseableByPlayer(Human player) {
        for (Human human : getViewers()) {
            if (human.getEntityID() == player.getEntityID()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addItems(ItemStack... items) {
        // todo
    }

    @Override
    public ItemStack[] getContents() {
        return (ItemStack[]) items.values().toArray();
    }

    @Override
    public void setContents(ItemStack[] items) {
        // this.items.values().add(items);
        // TODO
    }

    @Override
    public boolean contains(int materialId) {
        for (ItemStack item : items.values()) {
            if (item.getId() == materialId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item) {
        for (ItemStack items : this.items.values()) {
            if (items.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public List<Human> getViewers() {
        return viewers;
    }

    @Override
    public InventoryType getType() {
        return type;
    }

    @Override
    public void closeInventory(Human cyanHuman) {
        // TODO Auto-generated method stub

    }

    @Override
    public void openInventory(Human cyanHuman) {
        // TODO Auto-generated method stub

    }
}
package net.CyanWool.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemStack;

public class Register {

    private static List<BlockType> blocks = new ArrayList<BlockType>();
    private static List<ItemStack> items = new ArrayList<ItemStack>();

    public static boolean registerItem(ItemStack item) {
        if (!hasItem(item.getId())) {
            items.add(item);
            return true;
        }
        return false;
    }

    public static boolean registerBlock(BlockType block) {
        if (!hasBlock(block.getID())) {
            blocks.add(block);
            return true;
        }
        return false;
    }

    public static boolean hasItem(int id) {
        for (ItemStack item : items) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBlock(int id) {
        for (BlockType block : blocks) {
            if (block.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeItem(ItemStack item) {
        Iterator<ItemStack> it = items.iterator();
        while (it.hasNext()) {
            ItemStack i = it.next();
            if (i.getId() == item.getId()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean removeBlock(BlockType type) {
        Iterator<BlockType> it = blocks.iterator();
        while (it.hasNext()) {
            BlockType t = it.next();
            if (t.getID() == type.getID()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemStack(int id) {
        return getItemStack(id, 0);
    }

    public static ItemStack getItemStack(int id, int data) {
        for (ItemStack item : items) {
            if (item.getId() == id && item.getData() == data) {
                return item.clone();
            }
        }
        return null;
    }

    public static BlockType getBlock(int id) {
        return getBlock(id, 0);
    }

    public static BlockType getBlock(int id, int data) {
        for (BlockType block : blocks) {
            if (block.getID() == id && block.getData() == data) {
                return block.cloneBlock();
            }
        }
        return blocks.get(0);
    }

    public static List<ItemStack> getItems() {
        return items;
    }

    public static List<BlockType> getBlocks() {
        return blocks;
    }
}
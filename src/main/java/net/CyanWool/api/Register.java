package net.CyanWool.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.inventory.ItemType;

public class Register {

    private static List<BlockType> blocks = new ArrayList<BlockType>();
    private static List<ItemType> items = new ArrayList<ItemType>();

    public static boolean registerItem(ItemType item) {
        if (!hasItem(item.getId(), item.getData())) {
            items.add(item);
            return true;
        }
        return false;
    }

    public static boolean registerBlock(BlockType block) {
        if (!hasBlock(block.getID(), block.getData())) {
            blocks.add(block);
            return true;
        }
        return false;
    }

    public static boolean hasItem(int id, int data) {
        for (ItemType item : items) {
            if (item.getId() == id && item.getData() == data) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBlock(int id, int data) {
        for (BlockType block : blocks) {
            if (block.getID() == id && block.getData() == data) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeItem(ItemType item) {
        Iterator<ItemType> it = items.iterator();
        while (it.hasNext()) {
            ItemType i = it.next();
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

    public static ItemType getItemType(int id) {
        return getItemType(id, 0);
    }

    public static ItemType getItemType(int id, int data) {
        for (ItemType item : items) {
            if (item.getId() == id && item.getData() == data) {
                return item;
            }
        }
        return new ItemType(0, 0, (short) 0, 0, false);
    }

    public static BlockType getBlock(int id) {
        return getBlock(id, 0);
    }

    public static BlockType getBlock(int id, int data) {
        for (BlockType block : blocks) {
            if (block.getID() == id && block.getData() == data) {
                return block;
            }
        }
        return blocks.get(0);
    }

    public static List<ItemType> getItems() {
        return items;
    }

    public static List<BlockType> getBlocks() {
        return blocks;
    }
}
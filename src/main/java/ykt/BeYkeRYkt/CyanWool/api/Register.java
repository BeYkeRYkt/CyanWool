package ykt.BeYkeRYkt.CyanWool.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ykt.BeYkeRYkt.CyanWool.api.block.BlockType;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;

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
        if (!hasBlock(block.getID()) && !hasCustomBlock(block.getCustomID())) {
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

    public static boolean hasCustomBlock(String id) {
        for (BlockType block : blocks) {
            if (block.getCustomID().equals(id)) {
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
                if (type.hasCustomID() && t.hasCustomID()) {
                    if (type.getCustomID().equals(t.getCustomID())) {
                        it.remove();
                        return true;
                    }
                } else if (!type.hasCustomID() && !t.hasCustomID()) {
                    it.remove();
                    return true;
                }

            }
        }
        return false;
    }

    public static ItemStack getItemStack(int id) {
        for (ItemStack item : items) {
            if (item.getId() == id) {
                return item.clone();
            }
        }
        return null;
    }

    public static BlockType getBlock(int id) {
        for (BlockType block : blocks) {
            if (block.getID() == id) {
                return block.clone();
            }
        }
        return null;
    }

    public static BlockType getCustomBlock(String id) {
        for (BlockType block : blocks) {
            if (block.getCustomID().equals(id)) {
                return block;
            }
        }
        return null;
    }

    public static List<ItemStack> getItems() {
        return items;
    }

    public static List<BlockType> getBlocks() {
        return blocks;
    }
}
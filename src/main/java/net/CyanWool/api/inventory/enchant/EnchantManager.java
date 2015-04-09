package net.CyanWool.api.inventory.enchant;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnchantManager {

    private static List<Enchantment> enchants = new CopyOnWriteArrayList<Enchantment>();

    public static boolean registerEnchant(Enchantment enchant) {
        if (!enchants.contains(enchant)) {
            enchants.add(enchant);
            return true;
        }
        return false;
    }

    public static boolean removeEnchant(Enchantment enchant) {
        if (enchants.contains(enchant)) {
            enchants.remove(enchant);
            return true;
        }
        return false;
    }

    public Enchantment getByID(int id) {
        for (Enchantment enchantment : enchants) {
            if (enchantment.getID() == id) {
                return enchantment;
            }
        }
        return null;
    }

    public Enchantment getByName(String name) {
        for (Enchantment enchantment : enchants) {
            if (enchantment.getStringID().equals(name)) {
                return enchantment;
            }
        }
        return null;
    }

}
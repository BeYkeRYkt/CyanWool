package net.CyanWool.api.inventory.enchant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnchantManager{
    
    private static List<Enchantment> enchants = new ArrayList<Enchantment>();
    
    public static void registerEnchant(Enchantment enchant){
        if(!enchants.contains(enchant)){
            enchants.add(enchant);
        }
    }
    
    public static void removeEnchant(Enchantment enchant){
        if(enchants.contains(enchant)){
            Iterator<Enchantment> it = enchants.iterator();
            while(it.hasNext()){
                Enchantment e = it.next();
                if(e.equals(enchant)){
                    it.remove();
                    break;
                }
            }
        }
    }
    
    public Enchantment getByID(int id){
        for(Enchantment enchantment : enchants){
            if(enchantment.getID() == id){
                return enchantment;
            }
        }
        return null;
    }
    
    public Enchantment getByName(String name){
        for(Enchantment enchantment : enchants){
            if(enchantment.getStringID().equals(name)){
                return enchantment;
            }
        }
        return null;
    }
    
}
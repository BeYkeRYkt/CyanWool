package ykt.BeYkeRYkt.CyanWool.api.inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemData {

    private short durability;
    private short maxDurability;
    private String displayName;
    private List<String> lore;

    public ItemData(short maxDurability) {
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
        this.lore = new ArrayList<String>();
    }

    /**
     * @return the durability
     */
    public short getDurability() {
        return durability;
    }

    /**
     * @param durability
     *            the durability to set
     */
    public void setDurability(short durability) {
        this.durability = durability;
    }

    /**
     * @return the maxDurability
     */
    public short getMaxDurability() {
        return maxDurability;
    }

    /**
     * @param maxDurability
     *            the maxDurability to set
     */
    public void setMaxDurability(short maxDurability) {
        this.maxDurability = maxDurability;
    }

    public boolean hasDisplayName() {
        return getDisplayName() != null;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean hasLore() {
        return getLore() != null;
    }

    /**
     * @return the lore
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * @param lore
     *            the lore to set
     */
    public void setLore(List<String> lore) {
        this.lore = lore;
    }

}
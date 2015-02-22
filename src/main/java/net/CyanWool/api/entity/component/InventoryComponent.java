package net.CyanWool.api.entity.component;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.inventory.inventories.EntityInventory;

/**
 * Инвентарь Entity
 * 
 * @author DinDev
 */
public class InventoryComponent extends Component {

    private EntityInventory inv;

    public InventoryComponent(Entity entity, EntityInventory inv) {
        super(entity);
        this.inv = inv;
    }

    public EntityInventory getInventory() {
        return inv;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        // Maybe todo...
        // if(inv.isChanged()){
    }

    @Override
    public boolean autoUpdate() {
        // Maybe true ?
        return false;
    }

    @Override
    public String getID() {
        return "inventory";
    }

}
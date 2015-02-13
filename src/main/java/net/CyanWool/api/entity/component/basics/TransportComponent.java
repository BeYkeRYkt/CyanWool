package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.Component;

/**
 * Транспортировка Entity
 * 
 * @author DinDev
 */
public class TransportComponent extends Component {

    private Entity vehicle;
    private Entity passenger;

    public TransportComponent(Entity vehicle) {
        super(vehicle);
        this.vehicle = vehicle;
    }

    public Entity getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Entity entity) {
        this.vehicle = entity;
    }

    public Entity getPassenger() {
        return passenger;
    }

    public void setPassenger(Entity passenger) {
        this.passenger = passenger;
    }

    @Override
    public void update() {
        getPassenger().teleport(getVehicle().getLocation());
    }

    @Override
    public boolean autoUpdate() {
        return true;
    }

    @Override
    public String getID() {
        return "transport";
    }

}
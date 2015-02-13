package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.Component;

/**
 * XP компонент для игроков
 * 
 * @author DinDev
 */
public class XPComponent extends Component {

    private int xpLevel;
    private int xpTotal;

    public XPComponent(Entity entity) {
        super(entity);
    }

    public int getXPLevel() {
        return xpLevel;
    }

    public int getXPTotal() {
        return xpTotal;
    }

    public void setXPLevel(int level) {
        this.xpLevel = level;
    }

    public void setXPTotal(int xp) {
        this.xpTotal = xp;
    }

    @Override
    public String getID() {
        return "xp";
    }

}
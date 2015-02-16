package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.ai.EntityAITasks;
import net.CyanWool.api.entity.component.Component;

/**
 * Исскуственный интеллект.
 * 
 * @author DinDev
 */
public class AIComponent extends Component {

    private EntityAITasks tasks;
    private EntityAITasks targetTask;
    private boolean aiEnabled;
    private EntityLivingBase target;

    public AIComponent(Entity entity) {
        super(entity);
    }

    public EntityAITasks getTargetAITasks() {
        return targetTask;
    }

    public EntityAITasks getAITasks() {
        return tasks;
    }

    public boolean isAIEnabled() {
        return aiEnabled;
    }

    public void setAIEnabled(boolean enable) {
        this.aiEnabled = enable;
    }

    public EntityLivingBase getTarget() {
        return target;
    }

    public void setTarget(EntityLivingBase entity) {
        this.target = entity;
    }

    @Override
    public void update() {
        getEntity().getMetadata().setMetadata(15, (byte) (this.aiEnabled ? 1 : 0));
        if (aiEnabled) {
            getTargetAITasks().onUpdateAI();
            getAITasks().onUpdateAI();
        }
    }

    @Override
    public void initialization() {
        this.targetTask = new EntityAITasks();
        this.tasks = new EntityAITasks();
    }

    @Override
    public boolean autoUpdate() {
        return true;
    }

    @Override
    public String getID() {
        return "ai";
    }

}
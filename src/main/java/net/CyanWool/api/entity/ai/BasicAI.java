package net.CyanWool.api.entity.ai;

public abstract class BasicAI {

    public abstract boolean shouldExecute();

    public void updateTask() {
    }

    public void startExecuting() {
    }

    public void resetTask() {
    }

    public boolean canContinue() {
        return shouldExecute();
    }
}
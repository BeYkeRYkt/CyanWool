package net.CyanWool.api.entity.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EntityAITasks {

    private List<BasicAI> aiList = new ArrayList<BasicAI>();
    private List<BasicAI> activeAIList = new LinkedList<BasicAI>();
    private int tickCount;
    private int tickRate = 3;

    public void addAI(BasicAI ai) {
        synchronized (aiList) {
            aiList.add(ai);
        }
    }

    public void removeAI(BasicAI ai) {
        synchronized (aiList) {
            aiList.remove(ai);
        }
    }

    public void clearAI() {
        synchronized (aiList) {
            aiList.clear();
        }
    }

    public void onUpdateAI() {
        ArrayList<BasicAI> list = new ArrayList<BasicAI>();
        Iterator<BasicAI> iterator;
        if (this.tickCount++ % this.tickRate == 0) {
            iterator = this.aiList.iterator();

            while (iterator.hasNext()) {
                BasicAI ai = iterator.next();
                if (this.activeAIList.contains(ai)) {
                    if (ai.canContinue()) {
                        continue;
                    }
                    ai.resetTask();
                    this.activeAIList.remove(ai);
                }
                if (ai.shouldExecute()) {
                    list.add(ai);
                    this.activeAIList.add(ai);
                }

            }

        } else {
            iterator = this.activeAIList.iterator();

            while (iterator.hasNext()) {
                BasicAI ai = iterator.next();
                if (!ai.canContinue()) {
                    ai.resetTask();
                    iterator.remove();
                }
            }
        }

        iterator = list.iterator();

        while (iterator.hasNext()) {
            BasicAI ai = iterator.next();
            ai.startExecuting();
        }

        iterator = this.activeAIList.iterator();

        while (iterator.hasNext()) {
            BasicAI ai = iterator.next();
            ai.updateTask();
        }
    }
}
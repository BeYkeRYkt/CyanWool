package ykt.BeYkeRYkt.CyanWool.api.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import ykt.BeYkeRYkt.CyanWool.api.plugin.Plugin;

public class HandlerList {

    private volatile RegListener[] handlers = null;

    private final EnumMap<EventPriority, ArrayList<RegListener>> handlerslots;

    private static ArrayList<HandlerList> allLists = new ArrayList<HandlerList>();

    public static void bakeAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.bake();
            }
        }
    }

    public static void unregisterAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (ArrayList<RegListener> list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    public static void unregisterAll(Plugin plugin) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(plugin);
            }
        }
    }

    public static void unregisterAll(Listener listener) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(listener);
            }
        }
    }

    public HandlerList() {
        handlerslots = new EnumMap<EventPriority, ArrayList<RegListener>>(EventPriority.class);
        for (EventPriority o : EventPriority.values()) {
            handlerslots.put(o, new ArrayList<RegListener>());
        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    public synchronized void register(RegListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener))
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    public void registerAll(Collection<RegListener> listeners) {
        for (RegListener listener : listeners) {
            register(listener);
        }
    }

    public synchronized void unregister(RegListener listener) {
        if (handlerslots.get(listener.getPriority()).remove(listener)) {
            handlers = null;
        }
    }

    public synchronized void unregister(Plugin plugin) {
        boolean changed = false;
        for (ArrayList<RegListener> list : handlerslots.values()) {
            for (ListIterator<RegListener> i = list.listIterator(); i.hasNext();) {
                if (i.next().getOwner().equals(plugin)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed)
            handlers = null;
    }

    public synchronized void unregister(Listener listener) {
        boolean changed = false;
        for (List<RegListener> list : handlerslots.values()) {
            for (ListIterator<RegListener> i = list.listIterator(); i.hasNext();) {
                if (i.next().getListener().equals(listener)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed)
            handlers = null;
    }

    public synchronized void bake() {
        if (handlers != null)
            return;
        List<RegListener> entries = new ArrayList<RegListener>();
        for (Entry<EventPriority, ArrayList<RegListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new RegListener[entries.size()]);
    }

    public RegListener[] getRegisteredListeners() {
        RegListener[] handlers;
        while ((handlers = this.handlers) == null)
            bake();
        return handlers;
    }

    public static ArrayList<RegListener> getRegisteredListeners(Plugin plugin) {
        ArrayList<RegListener> listeners = new ArrayList<RegListener>();
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<RegListener> list : h.handlerslots.values()) {
                        for (RegListener listener : list) {
                            if (listener.getOwner().equals(plugin)) {
                                listeners.add(listener);
                            }
                        }
                    }
                }
            }
        }
        return listeners;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<HandlerList>) allLists.clone();
        }
    }
}

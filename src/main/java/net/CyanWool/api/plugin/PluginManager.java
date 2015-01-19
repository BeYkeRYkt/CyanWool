package net.CyanWool.api.plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.event.Event;
import net.CyanWool.api.event.EventException;
import net.CyanWool.api.event.EventExecutor;
import net.CyanWool.api.event.EventHandler;
import net.CyanWool.api.event.EventPriority;
import net.CyanWool.api.event.HandlerList;
import net.CyanWool.api.event.Listener;
import net.CyanWool.api.event.RegListener;

public class PluginManager {

    private ArrayList<Plugin> plugins;
    private ArrayList<PluginDescriptionFile> prefiles;
    private final static File pluginsFolder = new File("plugins");

    public PluginManager() {
        if (!getPluginsFolder().exists()) {
            getPluginsFolder().mkdirs(); // create folder "plugins"
        }
    }

    public File getPluginsFolder() {
        return pluginsFolder;
    }

    public void loadPlugins() {
        File pluginsFolder = getPluginsFolder();

        plugins = new ArrayList<Plugin>();
        prefiles = new ArrayList<PluginDescriptionFile>();

        CyanWool.getServer().getLogger().info("Loading plugins...");
        for (File file : pluginsFolder.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".jar") && (!file.isDirectory())) {
                // Load
                try {
                    PluginDescriptionFile pFile = new PluginDescriptionFile(file);
                    prefiles.add(pFile);
                } catch (Exception e) {
                    CyanWool.getServer().getLogger().error("Failed to load plugin " + file.getName());
                    e.printStackTrace();
                }
            } else if (!file.getName().toLowerCase().endsWith(".jar") && (!file.isDirectory())) {
                CyanWool.getServer().getLogger().info("Skipping " + file.getName() + " not a plugin.");
            }
        }

        if (!prefiles.isEmpty()) {
            for (PluginDescriptionFile file : prefiles) {
                loadPlugin(file);
            }
        }
    }

    public void enablePlugins() {
        CyanWool.getServer().getLogger().info("Starting plugins");

        for (Plugin plugin : plugins) {
            if (!plugin.isEnable()) {
                enablePlugin(plugin);
            }
        }
    }

    public void disablePlugins() {
        CyanWool.getServer().getLogger().info("Disabling plugins");

        for (Plugin plugin : plugins) {
            if (plugin.isEnable()) {
                disablePlugin(plugin);
            }
        }
    }

    public void unloadPlugins() {
        Iterator<Plugin> plugins = getPlugins().iterator();
        while (plugins.hasNext()) {
            Plugin plugin = plugins.next();
            disablePlugin(plugin);
            plugins.remove();
        }
        getPlugins().clear();
        prefiles.clear();
    }

    public Plugin getPlugin(String name) {
        for (Plugin plugin : getPlugins()) {
            if (plugin.getFileDescription().getName().equals(name)) {
                return plugin;
            }
        }
        return null;
    }

    public PluginDescriptionFile getPluginDescriptionFile(File file) {
        try {
            PluginDescriptionFile pFile = new PluginDescriptionFile(file);
            return pFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadPlugin(PluginDescriptionFile pFile) {
        try {
            URLClassLoader loader = pFile.getClassLoader();
            Class<?> pluginClass = loader.loadClass(pFile.getMainClass());
            Object instance = pluginClass.newInstance();
            if (instance instanceof Plugin) {
                Plugin plugin = (Plugin) instance;
                plugin.init(pFile);
                plugin.onLoad();
                plugins.add(plugin);
                CyanWool.getServer().getLogger().info("Loaded " + pFile.getName());
            } else {
                CyanWool.getServer().getLogger().info("Skipping " + pFile.getName() + " not a plugin.");
            }

        } catch (Exception e) {
            CyanWool.getServer().getLogger().error("Failed to load plugin " + pFile.getName() + ".");
            e.printStackTrace();
        }
    }

    public void enablePlugin(Plugin plugin) {
        CyanWool.getServer().getLogger().info("Starting " + plugin.getFileDescription().getName());
        plugin.enable = true;
        plugin.onEnable();
    }

    public void disablePlugin(Plugin plugin) {
        CyanWool.getServer().getLogger().info("Disabling " + plugin.getFileDescription().getName());
        plugin.enable = false;
        HandlerList.unregisterAll(plugin);
        plugin.onDisable();
    }

    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }

    public <T extends Event> T callEvent(T event) {
        HandlerList handlers = event.getHandlers();
        RegListener[] listeners = handlers.getRegisteredListeners();

        if (listeners != null) {
            for (RegListener listener : listeners) {
                if (!listener.getOwner().isEnable()) {
                    continue;
                }
                try {
                    listener.getExecutor().execute(event);
                } catch (Throwable ex) {
                    CyanWool.getLogger().error("Could not pass event " + event.getEventName() + " to " + listener.getOwner().getClass().getName(), ex);
                }
            }
        }
        return event;
    }

    public void registerEvents(Listener listener, Plugin owner) {
        for (Map.Entry<Class<? extends Event>, Set<RegListener>> entry : createRegisteredListeners(listener, owner).entrySet()) {
            Class<? extends Event> delegatedClass = getRegistrationClass(entry.getKey());
            if (!entry.getKey().equals(delegatedClass)) {
                CyanWool.getLogger().error("Plugin attempted to register delegated event class " + entry.getKey() + ". It should be using " + delegatedClass + "!");
                continue;
            }
            getEventListeners(delegatedClass).registerAll(entry.getValue());
        }
    }

    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin owner) {
        if (!owner.isEnable()) {
            return;
        }
        getEventListeners(event).register(new RegListener(listener, executor, priority, owner));
    }

    private HandlerList getEventListeners(Class<? extends Event> type) {
        try {
            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (HandlerList) method.invoke(null);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    private Class<? extends Event> getRegistrationClass(Class<? extends Event> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlerList");
            return clazz;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Event.class) && Event.class.isAssignableFrom(clazz.getSuperclass())) {
                return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
            } else {
                throw new IllegalArgumentException("Unable to find handler list for event " + clazz.getName());
            }
        }
    }

    public Map<Class<? extends Event>, Set<RegListener>> createRegisteredListeners(final Listener listener, Plugin plugin) {
        Map<Class<? extends Event>, Set<RegListener>> ret = new HashMap<Class<? extends Event>, Set<RegListener>>();
        Method[] methods;
        try {
            methods = listener.getClass().getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            CyanWool.getLogger().error("Plugin " + plugin.getClass().getSimpleName() + " is attempting to register event " + e.getMessage() + ", which does not exist. Ignoring events registered in " + listener.getClass());
            return ret;
        }
        for (final Method method : methods) {
            final EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) {
                continue;
            }
            final Class<?> checkClass = method.getParameterTypes()[0];
            Class<? extends Event> eventClass;
            if (!Event.class.isAssignableFrom(checkClass) || method.getParameterTypes().length != 1) {
                CyanWool.getLogger().error("Wrong method arguments used for event type registered");
                continue;
            } else {
                eventClass = checkClass.asSubclass(Event.class);
            }
            method.setAccessible(true);
            Set<RegListener> eventSet = ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet<RegListener>();
                ret.put(eventClass, eventSet);
            }
            eventSet.add(new RegListener(listener, new EventExecutor() {

                @Override
                public void execute(Event event) throws EventException {
                    try {
                        if (!checkClass.isAssignableFrom(event.getClass())) {
                            throw new EventException("Wrong event type passed to registered method");
                        }
                        method.invoke(listener, event);
                    } catch (Throwable t) {
                        throw new EventException(t);
                    }
                }

            }, eh.priority(), plugin));
        }
        return ret;
    }
}
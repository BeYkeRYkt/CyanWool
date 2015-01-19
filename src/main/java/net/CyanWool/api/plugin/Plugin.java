package net.CyanWool.api.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.configuration.file.FileConfiguration;
import net.CyanWool.api.configuration.file.YamlConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Plugin {

    private PluginDescriptionFile file;
    private Logger logger;
    private File folder;
    protected boolean enable = false;

    private FileConfiguration newConfig = null;
    private File configFile = null;

    public Plugin() {
    }

    public void init(PluginDescriptionFile file) {
        this.file = file;
        this.folder = new File(CyanWool.getPluginManager().getPluginsFolder(), getFileDescription().getName());
        this.logger = LogManager.getLogger(getFileDescription().getName());
        this.configFile = new File(this.getDataFolder(), "config.yml");
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onLoad();

    public File getDataFolder() {
        return folder;
    }

    public PluginDescriptionFile getFileDescription() {
        return file;
    }

    public Logger getLogger() {
        return logger;
    }

    public boolean isEnable() {
        return enable;
    }

    // From Bukkit, Thanks guys...
    public FileConfiguration getConfig() {
        if (newConfig == null) {
            reloadConfig();
        }
        return newConfig;
    }

    public void reloadConfig() {
        newConfig = YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = getResource("config.yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);

            newConfig.setDefaults(defConfig);
        }
    }

    public void saveConfig() {
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            CyanWool.getLogger().error("Could not save config to " + configFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
    }

    public void saveResource(String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + this.getFileDescription().getName());
        }

        File outFile = new File(this.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        File outDir = new File(this.getDataFolder(), resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                CyanWool.getLogger().warn("Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            CyanWool.getLogger().error("Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }

    public InputStream getResource(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = getFileDescription().getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Plugin)) {
            return false;
        }
        return getFileDescription().getName().equals(((Plugin) obj).getFileDescription().getName());
    }
}
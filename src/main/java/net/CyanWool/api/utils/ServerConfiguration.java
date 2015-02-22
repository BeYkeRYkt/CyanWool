package net.CyanWool.api.utils;

import java.io.File;
import java.io.IOException;

import net.CyanWool.api.configuration.file.FileConfiguration;
import net.CyanWool.api.configuration.file.YamlConfiguration;

public class ServerConfiguration {

    private final File configFile;
    private FileConfiguration config;

    // General
    private String ip;
    private int port;
    private boolean online_mode;
    private int maxplayers;
    private String motd;
    private int view;

    // private int distance; //DEV

    // and more others...

    // from server.properties
    public ServerConfiguration(File configFile) {
        this.configFile = configFile;
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void init() {
        if (!configFile.exists()) {
            createConfig();
            init(); // re-init
            return;
        } else {
            restore();
        }
    }

    public void save() {
        try {
            config.set("host", getIPAdress());
            config.set("port", getPort());
            config.set("online-mode", isOnlineMode()); // for test
            config.set("max-players", getMaxPlayers());
            config.set("view-distance", getViewDistance());
            config.set("motd", getMotd());
            config.save(configFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createConfig() {
        try {
            config.set("host", "0.0.0.0");
            config.set("port", 25565);
            config.set("online-mode", false); // for test
            config.set("max-players", 20);
            config.set("view-distance", 8);
            config.set("motd", "CyanWool Server!");
            config.save(configFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void restore() {
        // set ip
        String ip = config.getString("host");
        if (ip != null) {
            this.ip = ip;
        } else {
            this.ip = "0.0.0.0";
        }

        // set port
        int port = Integer.parseInt(config.getString("port"));
        if (port != 0) {
            this.port = port;
        } else {
            this.port = 22565;
        }

        // set online-mode
        boolean mode = Boolean.parseBoolean(config.getString("online-mode"));
        this.setOnlineMode(mode);

        // set max-players
        int max = Integer.parseInt(config.getString("max-players"));
        this.setMaxPlayers(max);

        int viewDistance = Integer.parseInt(config.getString("max-players"));
        this.setViewDistance(viewDistance);

        // set motd
        String motd = config.getString("motd");
        this.setMotd(motd);
    }

    public String getIPAdress() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isOnlineMode() {
        return online_mode;
    }

    public void setOnlineMode(boolean online_mode) {
        this.online_mode = online_mode;
    }

    public int getMaxPlayers() {
        return maxplayers;
    }

    public void setMaxPlayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public int getViewDistance() {
        return this.view;
    }

    public void setViewDistance(int view) {
        this.view = view;
    }
}
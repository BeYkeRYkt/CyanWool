package net.devwool.cyanwool.api;

import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;
import net.devwool.cyanwool.api.network.NetworkServer;

import org.apache.logging.log4j.Logger;

public class CyanWool {

    private static Server server;

    /**
     * 
     * Инициализация сервера
     * 
     * @param init
     *            - Сервер
     */
    public static void initServer(Server init) {
        if (getServer() != null) {
            server.getLogger().warn("Cannot redefine singleton Server");
            return;
        }
        server = init;
        getServer().getLogger().info("Server has been loaded...");
        getServer().getLogger().info("#====#_CyanWool_#====#");
        getServer().getLogger().info("Mod Name: " + getServer().getModName());
        getServer().getLogger().info("Host Address: " + getServer().getNetworkServer().getHostAddress());
        getServer().getLogger().info("Port: " + getServer().getNetworkServer().getPort());
        getServer().getLogger().info("Minecraft Version: " + getServer().getMCVersion());
    }

    /**
     * Возвращает сервер
     * 
     * @return Сервер
     */
    public static Server getServer() {
        return server;
    }

    /**
     * 
     * Название реализационого сервера
     * 
     * @return Название
     */
    public static String getModName() {
        return getServer().getModName();
    }

    /**
     * Версия Minecraft
     * 
     * @return Версия
     */
    public static String getMCVersion() {
        return getServer().getMCVersion();
    }

    /**
     * Возвращает логгер
     * 
     * @return Логгер из org.apache.logging.log4j
     */
    public static Logger getLogger() {
        return getServer().getLogger();
    }

    /**
     * Менеджер белого списка
     * 
     * @return
     */
    public static WhitelistManager getWhitelistManager() {
        return getServer().getWhitelistManager();
    }

    /**
     * Менеджер операторов
     * 
     * @return
     */
    public static OperatorsManager getOperatorsManager() {
        return getServer().getOperatorsManager();
    }

    /**
     * Менеджер для создания класса игрока и его взаимнодействия. Например заход
     * игрока на сервер.
     * 
     * @return
     */
    public static PlayerManager getPlayerManager() {
        return getServer().getPlayerManager();
    }

    /**
     * Сервер для обработки с пакетами.
     * 
     * @return
     */
    public static NetworkServer getNetworkServer() {
        return getServer().getNetworkServer();
    }

    /**
     * Отправить сообщение в глобальный чат (В том числе сервер).
     * 
     * @param message
     *            - Сообщение
     */
    public void broadcastMessage(String message) {
        getServer().broadcastMessage(message);
    }
}
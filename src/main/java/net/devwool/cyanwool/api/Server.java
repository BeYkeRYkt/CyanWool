package net.devwool.cyanwool.api;

import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;
import net.devwool.cyanwool.api.network.NetworkServer;

import org.apache.logging.log4j.Logger;

public interface Server {

    /**
     * Название реализационого сервера
     * 
     * @return Название
     */
    public String getModName();

    /**
     * Версия Minecraft
     * 
     * @return Версия
     */
    public String getMCVersion();

    /**
     * Отправить сообщение в глобальный чат (В том числе сервер).
     * 
     * @param message
     *            - Сообщение
     */
    public void broadcastMessage(String string);

    /**
     * Возвращает логгер
     * 
     * @return Логгер из org.apache.logging.log4j
     */
    public Logger getLogger();

    /**
     * Менеджер белого списка
     * 
     * @return
     */
    public WhitelistManager getWhitelistManager();

    /**
     * Менеджер операторов
     * 
     * @return
     */
    public OperatorsManager getOperatorsManager();

    /**
     * Менеджер для создания класса игрока и его взаимнодействия. Например заход
     * игрока на сервер.
     * 
     * @return
     */
    public PlayerManager getPlayerManager();

    /**
     * Сервер для обработки с пакетами.
     * 
     * @return
     */
    public NetworkServer getNetworkServer();

}
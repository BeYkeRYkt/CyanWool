package ykt.BeYkeRYkt.CyanWool.api.entity;

import java.util.UUID;

import ykt.BeYkeRYkt.CyanWool.api.Gamemode;
import ykt.BeYkeRYkt.CyanWool.api.command.ICommandSender;

public interface Player extends Human, ICommandSender {

    public void kickPlayer(String message);

    // public PlayerInventory getInventory();

    public UUID getUniqueId();

    public boolean isOp();

    public void setOp(boolean flag);

    public void executeCommand(String commandName);

    public void chat(String message);

    public int getPing();

    public boolean isBanned();

    public void setBanned(boolean banned);

    public boolean isWhitelisted();

    public void setWhitelisted(boolean whitelisted);

    // public void displayGUI(InventoryType type);

    public Gamemode getGameMode();

    public void setGamemode(Gamemode mode);
}
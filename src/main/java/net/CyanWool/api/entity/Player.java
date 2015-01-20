package net.CyanWool.api.entity;

import java.util.List;
import java.util.UUID;

import net.CyanWool.api.Gamemode;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.meta.ClientSettings;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.api.world.chunks.ChunkCoords;

import org.spacehq.mc.protocol.data.game.values.setting.SkinPart;

public interface Player extends Human, ICommandSender {

    public void kickPlayer(String message);

    // public PlayerInventory getInventory();

    public UUID getUniqueId();

    public boolean isOp();

    public void setOp(boolean flag);

    public void executeCommand(String commandName);

    public void chat(String message);

    public boolean isBanned();

    public void setBanned(boolean banned);

    public boolean isWhitelisted();

    public void setWhitelisted(boolean whitelisted);

    // public void displayGUI(InventoryType type);

    public Gamemode getGameMode();

    public void setGamemode(Gamemode mode);
    
    public List<ChunkCoords> getChunks();
    
    public List<SkinPart> getVisibleParts();//DevTest
    
    public PlayerNetwork getPlayerNetwork();
    
    public ClientSettings getSettings();
    
    public void setSettings(ClientSettings settings);

    //For NBT
    public void load();
    
    public void save();
}
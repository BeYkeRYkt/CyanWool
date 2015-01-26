package net.CyanWool.api.entity.player;

import java.util.List;
import java.util.UUID;

import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.world.chunks.ChunkCoords;

public interface Player extends Human, ICommandSender {

    public void kickPlayer(String message);

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

    public List<ChunkCoords> getChunks();
    
    public void playSound(String sound, float volume, float pitch);
}
package net.CyanWool.api.entity.player;

import java.util.List;
import java.util.UUID;

import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.ChunkCoords;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

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

    public void playSound(Location location, String sound, float volume, float pitch);

    public void playSound(Location location, Sound sound, float volume, float pitch);

    public void playEffect(Location location, WorldEffect effect, WorldEffectData data);

    public void playParticle(Location location, Particle particle, int amount, int data);

    public void setTime(long time); // maybe sendTimeUpdate ?

    public void sendChunk(Chunk chunk);

    public void updateChunk(Chunk chunk);

    public void respawn();

    public PlayerNetwork getPlayerNetwork();
}
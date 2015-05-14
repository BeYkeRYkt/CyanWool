package net.devwool.cyanwool.api.entity.player;

import java.util.List;

import net.devwool.cyanwool.api.command.ICommandSender;
import net.devwool.cyanwool.api.management.PlayerData;
import net.devwool.cyanwool.api.network.PlayerNetwork;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.chunk.Chunk;
import net.devwool.cyanwool.api.world.chunk.ChunkCoords;

import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public interface Player extends Human, ICommandSender {

    public void kickPlayer(String message);

    public PlayerData getPlayerData();

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

    public void playSound(Position pos, String sound, float volume, float pitch);

    public void playSound(Position pos, Sound sound, float volume, float pitch);

    public void playEffect(Position pos, WorldEffect effect, WorldEffectData data);

    public void playParticle(Position pos, Particle particle, int amount, int data);

    public void setTime(long time); // maybe sendTimeUpdate ?

    public void sendChunk(Chunk chunk);

    public void updateChunk(Chunk chunk);

    public void respawn();

    public PlayerNetwork getPlayerNetwork();
}
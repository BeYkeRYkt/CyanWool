package net.CyanWool.api.block;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class BlockSound {

    private final String name;
    private float volume;
    private float pitch;
    private GenericSound sound;

    public BlockSound(String name, float pitch, float volume) {
        this.name = name;
        this.pitch = pitch;
        this.volume = volume;
        this.sound = null;
    }

    public BlockSound(GenericSound sound, float pitch, float volume) {
        this(MagicValues.value(String.class, sound), volume, volume); // ???
        this.sound = sound;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float f) {
        this.volume = f;
    }

    public String getName() {
        return this.name;
    }

    public GenericSound getSound() {
        return sound;
    }

    public void setSound(GenericSound sound) {
        this.sound = sound;
    }
}
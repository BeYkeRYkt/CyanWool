package net.CyanWool.api.block;

import net.CyanWool.api.Sound;

public class BlockSound {

    private final String name;
    private float volume;
    private float pitch;
    private Sound sound;

    public BlockSound(String name, float pitch, float volume) {
        this.name = name;
        this.pitch = pitch;
        this.volume = volume;
        this.sound = Sound.CLICK;// ?
    }

    public BlockSound(Sound sound, float pitch, float volume) {
        this("sound.unknown", pitch, volume);
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

    public Sound getSound() {
        return sound;
    }
}
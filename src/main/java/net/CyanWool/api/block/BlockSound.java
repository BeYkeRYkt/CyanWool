package net.CyanWool.api.block;


public class BlockSound {

    private final String name;
    private float volume;
    private float pitch;

    public BlockSound(String name, float pitch, float volume) {
        this.name = name;
        this.pitch = pitch;
        this.volume = volume;
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
}
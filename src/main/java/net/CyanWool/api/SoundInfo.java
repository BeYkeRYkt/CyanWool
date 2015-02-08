package net.CyanWool.api;

//import org.spacehq.mc.protocol.data.game.values.world.Sound;

public class SoundInfo {

    private final String name;
    private float volume;
    private float pitch;
    private Sound sound;

    public SoundInfo(String name, float pitch, float volume) {
        this.name = name;
        this.pitch = pitch;
        this.volume = volume;
        this.sound = Sound.CLICK;// ?
    }
    
    public SoundInfo(Sound sound, float pitch, float volume){
        this("null", pitch, volume);
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
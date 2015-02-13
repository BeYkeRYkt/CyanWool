package net.CyanWool;

import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.world.Effect;
import net.CyanWool.api.world.Sound;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.MobType;
import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.effect.BreakBlockEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.BreakPotionEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.HardLandingEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.RecordEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.SmokeEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.SoundEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public class Transform {

    public static org.spacehq.mc.protocol.data.game.values.world.Sound convertLibSound(Sound sound) {
        if (GenericSound.valueOf(sound.name()) != null) {
            return GenericSound.valueOf(sound.name());
        }
        return GenericSound.CLICK;
    }

    public static WorldEffect convertWorldEffect(Effect effect) {
        if (ParticleEffect.valueOf(effect.name()) != null) {
            return ParticleEffect.valueOf(effect.name());
        } else if (SoundEffect.valueOf(effect.name()) != null) {
            return SoundEffect.valueOf(effect.name());
        }
        return ParticleEffect.SMOKE;
    }

    public static WorldEffectData convertWorldEffectData(Effect effect, int data) {
        org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData effectData = MagicValues.key(SmokeEffectData.class, data);
        if (effect == Effect.PLAY_RECORD) {
            effectData = new RecordEffectData(data);
        } else if (effect == Effect.BREAK_BLOCK) {
            effectData = new BreakBlockEffectData(data);
        } else if (effect == Effect.BREAK_SPLASH_POTION) {
            effectData = new BreakPotionEffectData(data);
        } else if (effect == Effect.HARD_LANDING_DUST) {
            effectData = new HardLandingEffectData(data);
        }
        return effectData;
    }
    
    public static MobType convertMobType(EntityType type){
        if(MobType.valueOf(type.name()) != null){
            return MobType.valueOf(type.name());
        }
        return MobType.SQUID;
    }

}
package com.pany.mods.vapi.Sounds;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import static com.pany.mods.vapi.Commands.CommandHelper.getPlayerFromContext;

public class SoundHelper {
    /**
     Play local sounds to a specific player
     */
    public static <RegistryEntryOrSoundEvent, PlayerOrCommandContext> void playLocalSound(PlayerOrCommandContext Targetting, RegistryEntryOrSoundEvent OriginalSound, SoundCategory category, float volume, float pitch) {
        PlayerEntity Target = null;
        RegistryEntry<SoundEvent> SoundEntry = null;
        // Getting Player
        if (Targetting instanceof PlayerEntity plr) {
            Target = plr;
        } else if (Targetting instanceof CommandContext) {
            Target = getPlayerFromContext((CommandContext<ServerCommandSource>) Targetting);
        }
        // Getting Entry
        if (OriginalSound instanceof RegistryEntry) {
            SoundEntry = (RegistryEntry<SoundEvent>) OriginalSound;
        } else if (OriginalSound instanceof SoundEvent soundEvent) {
            try {
                SoundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
            } catch (Exception ignored) {
            }
        }
        // Playing sound
        if (Target != null && SoundEntry != null) {
            if (Target.getWorld().isClient) {
                Target.getWorld().playSound(null, Target.getBlockPos(), SoundEntry.value(), category, volume, pitch);
            } else {
                ((ServerPlayerEntity) Target).networkHandler.sendPacket(new PlaySoundS2CPacket(SoundEntry, category, Target.getX(), Target.getY(), Target.getZ(), volume, pitch, Target.getRandom().nextLong()));
            }
        }
    }

    public static <RegistryEntryOrSoundEvent, PlayerOrCommandContext> void playLocalSound(PlayerOrCommandContext Targetting, RegistryEntryOrSoundEvent OriginalSound, float volume, float pitch) {
        playLocalSound(Targetting,OriginalSound,SoundCategory.NEUTRAL,volume,pitch);
    }
    public static <RegistryEntryOrSoundEvent, PlayerOrCommandContext> void playLocalSound(PlayerOrCommandContext Targetting, RegistryEntryOrSoundEvent OriginalSound) {
        playLocalSound(Targetting, OriginalSound, SoundCategory.NEUTRAL, 1, 1);
    }

    /**
     Play global sounds at a entity (only exists cause im lazy)
     */
    public static <RegistryEntryOrSoundEvent, PlayerOrCommandContext> void playSoundAtEntity(PlayerOrCommandContext Targetting, RegistryEntryOrSoundEvent OriginalSound,SoundCategory category,float volume,float pitch) {
        PlayerEntity Target = null;
        RegistryEntry<SoundEvent> SoundEntry = null;
        // Getting Player
        if (Targetting instanceof PlayerEntity plr) {
            Target = plr;
        } else if (Targetting instanceof CommandContext) {
            Target = getPlayerFromContext((CommandContext<ServerCommandSource>) Targetting);
        }

        // Getting Entry
        if (OriginalSound instanceof RegistryEntry) {
            SoundEntry = (RegistryEntry<SoundEvent>) OriginalSound;
        } else if (OriginalSound instanceof SoundEvent soundEvent) {
            try {
                SoundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
            } catch (Exception ignored) {
            }
        }
        // Playing sound
        Target.getWorld().playSound(null,Target.getX(),Target.getY(),Target.getZ(),SoundEntry,category,volume,pitch,Target.getRandom().nextLong());
    }
}

package com.pany.mods.vapi.Commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.EntitySelector;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class CommandHelper {
    @Nullable
    public static ServerPlayerEntity getPlayerFromContext(CommandContext<ServerCommandSource> context) {
        return context.getSource().getPlayer();
    }

    @Nullable
    private static ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context,String name) throws CommandSyntaxException {
        return context.getArgument(name, EntitySelector.class).getPlayer(context.getSource());
    }
}

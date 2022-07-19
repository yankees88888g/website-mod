package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.web.fabric.http.website.Website;

import static net.minecraft.server.command.CommandManager.literal;

public class Restart {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("restart_website") .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).executes(Restart::onRestart));
    }

    private static int onRestart(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        Website.restart();
        return 1;
    }
}

package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.web.fabric.http.website.Website;

import static net.minecraft.commands.Commands.literal;

public class Restart {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("restart_website") .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS)).executes(Restart::onRestart));
    }

    private static int onRestart(CommandContext<CommandSourceStack> serverCommandSourceCommandContext) {
        Website.restart();
        return 1;
    }
}

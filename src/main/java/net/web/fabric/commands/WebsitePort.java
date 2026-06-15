package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.web.fabric.http.website.Website;

import java.util.function.Supplier;

import static net.minecraft.commands.Commands.literal;

public class WebsitePort {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("get_website_port").executes(WebsitePort::onGetPort));
    }

    private static int onGetPort(CommandContext<CommandSourceStack> serverCommandSourceCommandContext) {
        Website.getServerPort();
        serverCommandSourceCommandContext.getSource().sendSuccess((Supplier<Component>) Component.nullToEmpty(String.valueOf(Website.getServerPort())),true);
        return 1;
    }
}

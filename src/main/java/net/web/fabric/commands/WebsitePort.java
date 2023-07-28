package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.web.fabric.http.website.Website;

import static net.minecraft.server.command.CommandManager.literal;

public class WebsitePort {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("get_website_port").executes(WebsitePort::onGetPort));
    }

    private static int onGetPort(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        Website.getServerPort();
        //serverCommandSourceCommandContext.getSource().sendFeedback(Text.of(String.valueOf(Website.getServerPort())),true);
        return 1;
    }
}

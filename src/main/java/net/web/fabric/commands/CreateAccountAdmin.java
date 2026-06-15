package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.web.fabric.http.website.login.cred.Encryption;

import java.util.function.Supplier;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CreateAccountAdmin {

    // creates command /create_account_admin [username] [password]

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("create_account_admin")
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(argument("username", StringArgumentType.string())
                        .then(argument("password", StringArgumentType.string())
                                .executes(CreateAccountAdmin::onCreateAccount)))


        );
    }

    public static int onCreateAccount(CommandContext<CommandSourceStack> context) {
        System.out.println("Player: " + context.getSource().getTextName());
        Encryption.write(context.getArgument("username", String.class), context.getArgument("password", String.class), true, context.getSource().getPlayer().getStringUUID(), context.getSource().getPlayer().getName().getString());
        context.getSource().sendSuccess((Supplier<Component>) Component.nullToEmpty("created account for " + context.getArgument("username", String.class)), true);
        return 1;
    }
}

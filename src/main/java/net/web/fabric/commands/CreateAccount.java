package net.web.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.web.fabric.http.website.login.cred.Encryption;

import static net.minecraft.server.command.CommandManager.*;

public class CreateAccount{

    // creates command /create_account [username] [password]
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("create_account")
            .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
            .then(argument("username", StringArgumentType.string()))
            .then(argument("password", StringArgumentType.string()))
            .executes(CreateAccount::onCreateAccount)
    );
}

    public static int onCreateAccount(CommandContext<ServerCommandSource> context) {
        System.out.println("Player: " + context.getSource().getName());
        Encryption.write(context.getArgument("username", String.class),context.getArgument("password", String.class));
        return 1;
    }
}

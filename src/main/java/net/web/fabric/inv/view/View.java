package net.web.fabric.inv.view;


import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.web.fabric.WebMain;

public class View {
    private static MinecraftServer minecraftServer = WebMain.getMinecraftServer();

    public static void main() {
    }
    private static ServerPlayerEntity getRequestedPlayer(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        GameProfile requestedProfile = GameProfileArgumentType.getProfileArgument(context, "target").iterator().next();
        ServerPlayerEntity requestedPlayer = minecraftServer.getPlayerManager().getPlayer(requestedProfile.getName());
        return requestedPlayer;
    }
}

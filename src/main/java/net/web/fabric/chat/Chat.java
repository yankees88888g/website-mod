package net.web.fabric.chat;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.web.fabric.WebMain;

import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Chat {
    private static MinecraftServer minecraftServer = WebMain.getMinecraftServer();

    public static void chat(String player, String uuid, String msg) {

        ServerPlayerEntity requestedPlayer = minecraftServer.getPlayerManager().getPlayer(player);
        if(requestedPlayer == null) {
            requestedPlayer = getRequestedPlayer(player, uuid);
        }
        if (isDisabled(requestedPlayer)) {
            return;
        } else {

            return;
        }
    }

    private static boolean isDisabled(ServerPlayerEntity requestedPlayer) {
        return false;
    }
}

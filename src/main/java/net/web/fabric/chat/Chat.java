package net.web.fabric.chat;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.web.fabric.WebMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static net.web.fabric.WebMain.LOGGER;
import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Chat {

    public static void chat(String player, String uuid, String msg) {

        ServerPlayer requestedPlayer = WebMain.getMinecraftServer().getPlayerList().getPlayerByName(player);
        if (requestedPlayer == null) {
            requestedPlayer = getRequestedPlayer(player, uuid);
        }
        if (isDisabled(requestedPlayer)) {
            return;
        } else {
            WebMain.getMinecraftServer().getPlayerList().broadcastSystemMessage(Component.literal("Web-").append(requestedPlayer.getDisplayName()).append(": ").append(msg), true);
            return;
        }
    }

    private static boolean isDisabled(ServerPlayer requestedPlayer) {
        return false;
    }


    public static void Listener(int port) throws Exception {
        URL oracle = new URL("http://localhost:" + port + "/");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            LOGGER.info(inputLine);
        in.close();
    }
}

package net.web.fabric.chat;

import net.minecraft.network.message.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.web.fabric.WebMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static net.web.fabric.WebMain.LOGGER;
import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Chat {

    public static void chat(String player, String uuid, String msg) {

        ServerPlayerEntity requestedPlayer = WebMain.getMinecraftServer().getPlayerManager().getPlayer(player);
        if (requestedPlayer == null) {
            requestedPlayer = getRequestedPlayer(player, uuid);
        }
        if (isDisabled(requestedPlayer)) {
            return;
        } else {
            WebMain.getMinecraftServer().getPlayerManager().broadcast(Text.literal("Web-").append(requestedPlayer.getDisplayName()).append(": ").append(msg), true);
            return;
        }
    }

    private static boolean isDisabled(ServerPlayerEntity requestedPlayer) {
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

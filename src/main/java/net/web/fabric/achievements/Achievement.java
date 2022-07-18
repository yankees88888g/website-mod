package net.web.fabric.achievements;

import net.minecraft.advancement.Advancement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.web.fabric.WebMain;

import java.util.Collection;

import static net.web.fabric.http.website.Website.LOGGER;
import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Achievement {
    private static MinecraftServer minecraftServer = WebMain.getMinecraftServer();

    public static void getAchievement(String player, String uuid) {
        try {
            ServerPlayerEntity requestedPlayer = minecraftServer.getPlayerManager().getPlayer(player);

            if (requestedPlayer == null) {
                requestedPlayer = getRequestedPlayer(player, uuid);
            }
            if (isProtected(requestedPlayer)) {
                return;
            } else {
                LOGGER.info("tes");
                Ach ach = new Ach(player);
                Collection<Advancement> advancements = minecraftServer.getAdvancementLoader().getAdvancements();
                int i = 0;
                for (Advancement a : advancements) {
                    ach.addAch(i, a, requestedPlayer.getAdvancementTracker().getProgress(a).isDone(), a.getId().toString());
                    i++;
                }
                ach.register();
                LOGGER.info(String.valueOf(ach));
                return;
            }
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
        }
    }

    private static boolean isProtected(ServerPlayerEntity requestedPlayer) {
        return false;
    }
}

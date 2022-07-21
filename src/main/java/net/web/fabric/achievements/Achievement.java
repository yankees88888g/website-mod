package net.web.fabric.achievements;

import net.minecraft.advancement.Advancement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.web.fabric.WebMain;

import java.util.Collection;

import static net.web.fabric.http.website.Website.LOGGER;
import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Achievement {

    public static void getAchievement(String player, String uuid) {
        try {
            ServerPlayerEntity requestedPlayer = WebMain.getMinecraftServer().getPlayerManager().getPlayer(player);

            if (requestedPlayer == null) {
                requestedPlayer = getRequestedPlayer(player, uuid);
            }
            if (isProtected(requestedPlayer)) {
                return;
            } else {
                Ach ach = new Ach(player);
                Collection<Advancement> advancements = WebMain.getMinecraftServer().getAdvancementLoader().getAdvancements();
                for (Advancement a : advancements) {
                    if (a.getDisplay() != null) {
                        ach.addAch( new AchData(a, requestedPlayer.getAdvancementTracker().getProgress(a).isDone()));
                    }
                }
               /* for (Advancement a : advancements) {
                    if (a.getDisplay() == null) {
                        ach.addAch(i, a, requestedPlayer.getAdvancementTracker().getProgress(a).isDone(), a.getId().toString(),null);
                    }
                    i++;
                }*/
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

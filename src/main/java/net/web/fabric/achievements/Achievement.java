package net.web.fabric.achievements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.level.ServerPlayer;
import net.web.fabric.WebMain;

import java.util.Collection;

import static net.web.fabric.WebMain.LOGGER;
import static net.web.fabric.inv.view.View.getRequestedPlayer;

public class Achievement {

    public static void getAchievement(String player, String uuid) {
        try {
            ServerPlayer requestedPlayer = WebMain.getMinecraftServer().getPlayerList().getPlayerByName(player);

            if (requestedPlayer == null) {
                requestedPlayer = getRequestedPlayer(player, uuid);
            }
            if (isProtected(requestedPlayer)) {
                return;
            } else {
                Ach ach = new Ach(player);
                Collection<AdvancementHolder> advancements = WebMain.getMinecraftServer().getAdvancements().getAllAdvancements();
                for (AdvancementHolder a : advancements) {
                    if (a.value().display() != null) {
                        ach.addAch( new AchData(a.value(), requestedPlayer.getAdvancements().getOrStartProgress(a).isDone()));
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

    private static boolean isProtected(ServerPlayer requestedPlayer) {
        return false;
    }
}

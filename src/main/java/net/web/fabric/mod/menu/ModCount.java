package net.web.fabric.mod.menu;

import net.web.fabric.mod.menu.config.ModMenuConfig;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ModCount {


    public static final Map<String, Mod> MODS = new HashMap<>();
    private static int cachedDisplayedModCount = -1;

    public static String getDisplayedModCount() {
        if (cachedDisplayedModCount == -1) {
            // listen, if you have >= 2^32 mods then that's on you
            cachedDisplayedModCount = Math.toIntExact(MODS.values().stream().filter(mod ->
                    (ModMenuConfig.COUNT_CHILDREN.getValue() || mod.getParent() == null) &&
                            (ModMenuConfig.COUNT_LIBRARIES.getValue() || !mod.getBadges().contains(Mod.Badge.LIBRARY)) &&
                            (ModMenuConfig.COUNT_HIDDEN_MODS.getValue() || !ModMenuConfig.HIDDEN_MODS.getValue().contains(mod.getId()))
            ).count());
        }
        return NumberFormat.getInstance().format(cachedDisplayedModCount);
    }
}

package net.web.fabric.mod.menu;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedListMultimap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.web.fabric.mod.menu.api.ConfigScreenFactory;
import net.web.fabric.mod.menu.api.ModMenuApi;
import net.web.fabric.mod.menu.config.ModMenuConfig;
import net.web.fabric.mod.menu.config.ModMenuConfigManager;
import net.web.fabric.mod.menu.event.ModMenuEventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModMenu{
    public static final String MOD_ID = "modmenu";
    public static final Logger LOGGER = LogManager.getLogger("Mod Menu");
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    public static final Map<String, Mod> MODS = new HashMap<>();
    public static final Map<String, Mod> ROOT_MODS = new HashMap<>();
    public static final LinkedListMultimap<Mod, Mod> PARENT_MAP = LinkedListMultimap.create();

    private static ImmutableMap<String, ConfigScreenFactory<?>> configScreenFactories = ImmutableMap.of();
    private static List<Supplier<Map<String, ConfigScreenFactory<?>>>> dynamicScreenFactories = new ArrayList<>();

    private static int cachedDisplayedModCount = -1;

    public static void onStart() {
        ModMenuConfigManager.initializeConfig();
        Map<String, ConfigScreenFactory<?>> factories = new HashMap<>();
        FabricLoader.getInstance().getEntrypointContainers("modmenu", ModMenuApi.class).forEach(entrypoint -> {
            ModMetadata metadata = entrypoint.getProvider().getMetadata();
            String modId = metadata.getId();
            try {
                ModMenuApi api = entrypoint.getEntrypoint();
                factories.put(modId, api.getModConfigScreenFactory());
                dynamicScreenFactories.add(api::getProvidedConfigScreenFactories);
            } catch (Throwable e) {
                LOGGER.error("Mod {} provides a broken implementation of ModMenuApi", modId, e);
            }
        });
        configScreenFactories = ImmutableMap.copyOf(factories);


        // Fill mods map
        for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
            if (!ModMenuConfig.HIDDEN_MODS.getValue().contains(modContainer.getMetadata().getId())) {
                    FabricMod mod = new FabricMod(modContainer);
                    MODS.put(mod.getId(), mod);
            }
        }

        Map<String, Mod> dummyParents = new HashMap<>();

        // Initialize parent map
        for (Mod mod : MODS.values()) {
            String parentId = mod.getParent();
            if (parentId != null) {
                Mod parent = MODS.getOrDefault(parentId, dummyParents.get(parentId));
                if (parent == null) {
                    if (mod instanceof FabricMod) {
                        parent = new FabricDummyParentMod((FabricMod) mod, parentId);
                        dummyParents.put(parentId, parent);
                    }
                }
                PARENT_MAP.put(parent, mod);
            } else {
                ROOT_MODS.put(mod.getId(), mod);
            }
        }
        MODS.putAll(dummyParents);
    }

    public static void clearModCountCache() {
        cachedDisplayedModCount = -1;
    }

    public static int getDisplayedModCount() {
        if (cachedDisplayedModCount == -1) {
            // listen, if you have >= 2^32 mods then that's on you
            cachedDisplayedModCount = Math.toIntExact(MODS.values().stream().filter(mod ->
                    (ModMenuConfig.COUNT_CHILDREN.getValue() || mod.getParent() == null) &&
                            (ModMenuConfig.COUNT_LIBRARIES.getValue() || !mod.getBadges().contains(Mod.Badge.LIBRARY)) &&
                            (ModMenuConfig.COUNT_HIDDEN_MODS.getValue() || !ModMenuConfig.HIDDEN_MODS.getValue().contains(mod.getId()))
            ).count());
        }
        return Integer.parseInt(NumberFormat.getInstance().format(cachedDisplayedModCount)) - 33; //fabric, fabric api, minecraft, java ect all make up 34 mods we only count fabric api and only once.
    }
}

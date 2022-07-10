package net.web.fabric.mod.menu;

import net.fabricmc.loader.api.metadata.ModMetadata;

public class LibMod {
    public static boolean isLibraryMod(ModMetadata metadata) {
        String id = metadata.getId();
        if (id.startsWith("fabric") && metadata.containsCustomValue("fabric-api:module-lifecycle")) {
            return true;
        }
        if (id.startsWith("fabric") && (id.equals("fabricloader") || metadata.getProvides().contains("fabricloader") || id.equals("fabric") || id.equals("fabric-api") || metadata.getProvides().contains("fabric") || metadata.getProvides().contains("fabric-api"))) {
            return true;
        }
        return (metadata.containsCustomValue("fabric-loom:generated") && metadata.getCustomValue("fabric-loom:generated").getAsBoolean()) || "java".equals(id);
    }
}

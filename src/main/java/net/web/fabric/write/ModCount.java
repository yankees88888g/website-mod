package net.web.fabric.write;

import net.web.fabric.mod.menu.ModMenu;

public class ModCount {
    public static String Count =
            "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>ModCount</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h2>\n" +
                    "Mods loaded\n" +
                    "</h2>" +
                    ModMenu.getDisplayedModCount() +
                    "</body>\n" +
                    "</html>";
}

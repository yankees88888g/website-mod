package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.minecraft.item.ItemStack;
import net.web.fabric.http.website.login.cred.Credentials;
import net.web.fabric.inv.view.Gui;
import net.web.fabric.inv.view.GuiEC;
import net.web.fabric.inv.view.View;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AInvHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Credentials cred = ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5");
        String player = ctx.queryParam("player");
        ctx.html(handleAInvResponse(cred, player));
    }

    public static String handleAInvResponse(Credentials credentials, String player) throws IOException {
        String response;
        if (credentials.admin == true) {
            View.inv(String.valueOf(player), View.getUUID(player));
            View.eChest(String.valueOf(player), View.getUUID(player));
            response = "<!DOCTYPE html><html lang=\"en\"><head><title>Inv " + player + "</title><style>body {-moz-transform: scale(2.0); /* for Firefox, default 1*/zoom:200%; /* For Chrome, IE, default 100%*/}</style></head><link rel=\"stylesheet\" href=\"https://www.gamergeeks.net/apps/minecraft/web-developer-tools/css-blocks-and-entities/icons-minecraft-0.5.css\"><link rel=\"stylesheet\" href=\"inv.css\"><body><h5 id=\"h\">Inventory of " + player + "</h5><br><img id= inv src=\"https://i.imgur.com/GxH81To.png\"><p>" + htmlBuilder(Gui.getInv(player)) + "</p><br><h5 id=\"hec\" >Ender Chest of " + player + "</h5><br><img id = ec src=\"https://i.imgur.com/HDAtaYU.png\"><p>" + htmlBuilderEC(GuiEC.getInv(player)) + "</p><br><input type=\"text\" name=\"player\" id=\"player\" class=\"player-field\" placeholder=\"Player Name\"><button type=\"submit\" id=\"submit\">Enter</button><script src=\"admin.js\"></script></body></html>";
        } else {
            response = HtmlHelper.redirect;
        }
        return response;
    }

    private static String htmlBuilderEC(GuiEC guiEC) {
        List<ItemStack> slots = guiEC.slots;
        StringBuilder html = new StringBuilder();
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            ItemStack slot = slots.get(j);
            if (j % 9 == 0) {
                html.append("<br>");
            }
            if (j < 9) {
                html.append(getHtmlFromItemStack(slot, 0, 9, true));
            } else if (j < 18) {
                html.append(getHtmlFromItemStack(slot, 0, 18, true));
            } else if (j < 27) {
                html.append(getHtmlFromItemStack(slot, 0, 27, true));
            }
        }
        html.append("<div class ECText>");
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            if (j == 0) {
                html.append("<div id=e" + 0 + ">");
            } else {
                html.append("</div><div id=e" + j + ">");
            }
            html.append(slots.get(j).getCount());
        }
        html.append("</div>");
        return html.toString();
    }

    private static String htmlBuilder(Gui gui) {
        List<ItemStack> slots = gui.slots;
        StringBuilder html = new StringBuilder();
        int i = 0;
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            ItemStack slot = slots.get(j);
            if (j % 9 == 0) {
                html.append("<br>");
            }
            if (j > 35) {
                i++;
                html.append(getHtmlFromItemStack(slot, i, 0, false));
            } else {
                if (j < 9) {
                    html.append(getHtmlFromItemStack(slot, 0, 9, false));
                } else if (j < 18) {
                    html.append(getHtmlFromItemStack(slot, 0, 18, false));
                } else if (j < 27) {
                    html.append(getHtmlFromItemStack(slot, 0, 27, false));
                } else {
                    html.append(getHtmlFromItemStack(slot, 0, 0, false));
                }
            }
        }
        html.append("<div class invText>");
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            if (j == 0) {
                html.append("<div id=n" + 0 + ">");
            } else {
                html.append("</div><div id=n" + j + ">");
            }
            html.append(slots.get(j).getCount());
        }
        html.append("</div>");
        return html.toString();
    }

    private static String getHtmlFromItemStack(ItemStack itemStack, int s36, int inv, boolean ec) {
        String a = String.valueOf(itemStack.getItem());
        String c = a.replace("_", "-");
        if (ec == true) {
            return "<i id=iconEC" + inv + " class=\"icon-minecraft icon-minecraft-" + c + "\"></i>";
        } else {
            if (s36 == 0) {
                if (inv != 0) {
                    return "<i id=icon" + inv + " class=\"icon-minecraft icon-minecraft-" + c + "\"></i>";
                } else {
                    return "<i id=icon class=\"icon-minecraft icon-minecraft-" + c + "\"></i>";
                }
            } else {
                return "<i id=icon" + s36 + " class=\"icon-minecraft icon-minecraft-" + c + "\"></i>";
            }
        }
    }
}

package net.web.fabric.http.website.handlers.gets;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.minecraft.item.ItemStack;
import net.web.fabric.http.website.handlers.HtmlHelper;
import net.web.fabric.http.website.login.cred.Credentials;
import net.web.fabric.inv.view.Gui;
import net.web.fabric.inv.view.GuiEC;
import net.web.fabric.inv.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InvHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Credentials cred = ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5");
        String response;
        if (cred != null) {
            View.inv(cred.playername, cred.uuid);
            View.eChest(cred.playername, cred.uuid);
            response = "<!DOCTYPE html><html lang=\"en\"><head><title>Inv " + cred.playername + "</title><style>body {-moz-transform: scale(2.0); /* for Firefox, default 1*/zoom:200%; /* For Chrome, IE, default 100%*/}</style></head><link rel=\"stylesheet\" href=\"https://www.gamergeeks.net/apps/minecraft/web-developer-tools/css-blocks-and-entities/icons-minecraft-0.5.css\"><link rel=\"stylesheet\" href=\"inv.css\"><body><h5 id=\"h\">Inventory</h5><br>" + "<img class=\"skin\" src=\"https://crafatar.com/renders/body/" + cred.uuid + "\"  alt=\"body\">" + "<br><img id=inv src=\"https://i.imgur.com/GxH81To.png\"><p>" + htmlBuilder(Gui.getInv(String.valueOf(cred.playername))) + "</p><br><h5 id=\"hec\" >Ender Chest</h5><br><img id = ec src=\"https://i.imgur.com/HDAtaYU.png\"><p>" + htmlBuilderEC(GuiEC.getInv(cred.playername)) + "</p></body></html>";
        } else {
            response = HtmlHelper.redirect;
        }
        ctx.html(response);
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

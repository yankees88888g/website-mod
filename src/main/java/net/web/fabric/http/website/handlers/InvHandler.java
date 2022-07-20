package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import net.minecraft.item.ItemStack;
import net.web.fabric.http.website.login.cred.Credentials;
import net.web.fabric.inv.view.Gui;
import net.web.fabric.inv.view.GuiEC;
import net.web.fabric.inv.view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class InvHandler {

    //nonadmin
    public static void handleInv(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        String response;
        if (Credentials.verify(exchange.getRemoteAddress().getAddress()) == 1) {
            View.inv(cred.playername, cred.uuid);
            View.eChest(cred.playername, cred.uuid);
            response = "<!DOCTYPE html><html lang=\"en\"><head><title>Inv " + cred.playername + "</title><style>body {-moz-transform: scale(2.0); /* for Firefox, default 1*/zoom:200%; /* For Chrome, IE, default 100%*/}</style></head><link rel=\"stylesheet\" href=\"https://www.gamergeeks.net/apps/minecraft/web-developer-tools/css-blocks-and-entities/icons-minecraft-0.5.css\"><body>Inventory<br>" + htmlBuilder(Gui.getInv(String.valueOf(cred.playername))) + "<br>Ender Chest<br>" + htmlBuilderEC(GuiEC.getInv(cred.playername)) + "</body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            response = HtmlHelper.redirect;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }

    //admin
    public static void handleAInv(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetARequest(httpExchange);
            handleAInvResponse(httpExchange, requestParamValue);
        } else {
            handleAInvResponse(httpExchange, requestParamValue);
        }
    }

    private static String handleGetARequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    public static void handleAInvResponse(HttpExchange exchange, String player) throws IOException {
        OutputStream os = exchange.getResponseBody();
        String response;
        if (Credentials.verifyAdmin(exchange.getRemoteAddress().getAddress()) == 1) {
            View.inv(String.valueOf(player), View.getUUID(player));
            View.eChest(String.valueOf(player), View.getUUID(player));
            response = "<!DOCTYPE html><html lang=\"en\"><head><title>Inv " + player + "</title><style>body {-moz-transform: scale(2.0); /* for Firefox, default 1*/zoom:200%; /* For Chrome, IE, default 100%*/}</style></head><link rel=\"stylesheet\" href=\"https://www.gamergeeks.net/apps/minecraft/web-developer-tools/css-blocks-and-entities/icons-minecraft-0.5.css\"><body>Inventory of " + player + "<br>" + htmlBuilder(Gui.getInv(player)) + "<br>Ender Chest of " + player + "<br>" + htmlBuilderEC(GuiEC.getInv(player)) + "<br><input type=\"text\" name=\"player\" id=\"player\" class=\"player-field\" placeholder=\"Player Name\"><button type=\"submit\" id=\"submit\">Enter</button><script src=\"admin.js\"></script></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            response = HtmlHelper.redirect;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }

    private static String htmlBuilderEC(GuiEC guiEC) {
        List<ItemStack> slots = guiEC.slots;
        StringBuilder html = new StringBuilder();
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            ItemStack slot = slots.get(j);
            if (j % 9 == 0) {
                html.append("<br>");
            }
            html.append(getHtmlFromItemStack(slot));
        }
        return html.toString();
    }
    private static String htmlBuilder(Gui gui) {
        List<ItemStack> slots = gui.slots;
        StringBuilder html = new StringBuilder();
        for (int j = 0, slotsSize = slots.size(); j < slotsSize; j++) {
            ItemStack slot = slots.get(j);
            if (j % 9 == 0) {
                html.append("<br>");
            }
            html.append(getHtmlFromItemStack(slot));
        }
        return html.toString();
    }
    private static String getHtmlFromItemStack(ItemStack itemStack) {
        String a = String.valueOf(itemStack.getItem());
        String c = a.replace("_", "-");
        int b = itemStack.getCount();
        return "<i class=\"icon-minecraft icon-minecraft-" + c + "\"></i>" + ":" + b + " ";
    }
}

package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.web.fabric.http.website.login.cred.Credentials;
import net.web.fabric.inv.view.Gui;
import net.web.fabric.inv.view.GuiEC;
import net.web.fabric.inv.view.View;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.WebMain.LOGGER;

public class InvHandler {
    public static String redirect = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /\" /></head><body><p>error redirecting</p></body></html>";

    //nonadmin

    public static void handleInv(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        String response;
        if (Credentials.verify(exchange.getRemoteAddress().getAddress()) == 1) {
            View.inv(String.valueOf(cred.playername), cred.uuid);
            View.eChest(String.valueOf(cred.playername), cred.uuid);
            Gui gui = Gui.getInv(String.valueOf(cred.playername));
            GuiEC guiEC = GuiEC.getInv(String.valueOf(cred.playername));
            StringBuilder html = new StringBuilder();
            for (ItemStack slot : gui.slots) {
                String a = String.valueOf(slot.getItem());
                int b = slot.getCount();
                html.append("<img src=\""+ a +".png\"" + a + ">:" + b + " ");
            }
            StringBuilder ec = new StringBuilder();
            for (ItemStack slot : guiEC.slots) {
                String a = String.valueOf(slot.getItem());
                int b = slot.getCount();
                ec.append(a + ":" + b + " ");
            }
            response = "<html><body>Inventory<br>" + html + "<br>Ender chest<br>" + ec + "</body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            response = redirect;
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
            Gui gui = Gui.getInv(String.valueOf(player));
            GuiEC guiEC = GuiEC.getInv(String.valueOf(player));
            StringBuilder html = new StringBuilder();
            for (ItemStack slot : gui.slots) {
                String a = String.valueOf(slot.getItem());
                int b = slot.getCount();
                html.append(a + ":" + b + " ");
            }
            StringBuilder ec = new StringBuilder();
            for (ItemStack slot : guiEC.slots) {
                String a = String.valueOf(slot.getItem());
                int b = slot.getCount();
                ec.append(a + ":" + b + " ");
            }
            response = "<html><body>Inventory of " + player + "<br>" + html + "<br>Ender chest<br>" + ec + "</body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            response = redirect;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}

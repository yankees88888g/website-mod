package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import net.minecraft.item.ItemStack;
import net.web.fabric.http.website.login.cred.Credentials;
import net.web.fabric.inv.view.Gui;
import net.web.fabric.inv.view.View;

import java.io.IOException;
import java.io.OutputStream;

public class InvHandler {
    public static String redirect = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /\" /></head><body><p>error redirecting</p></body></html>";

    //nonadmin

    public static void handleInv(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        String response;
        if (!exchange.getRemoteAddress().getAddress().equals(cred.address)) {
            response = redirect;
        } else {//TODO fix error in side this else statement
            View.inv(String.valueOf(cred.playername),cred.uuid);
            Gui gui = Gui.getInv(String.valueOf(cred.playername));
            StringBuilder html = new StringBuilder();
            for (ItemStack slot : gui.slots) {
                String a = String.valueOf(slot.getItem());
                int b = slot.getCount();
                html.append(a + " " + b);
            }
            response = "<html><body>Inventory and ender chest<br>" + html + "</body></html>";
        }
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
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

    public static void handleAInvResponse(HttpExchange exchange, String requestParamValue) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        boolean admin = cred.admin;
        String response;
        if (admin == true) {
            response = "Inventory of " + cred.username;
        } else {
            response = redirect;
        }
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
}
